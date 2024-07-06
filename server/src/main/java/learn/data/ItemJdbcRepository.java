package learn.data;

import learn.data.mappers.CategoryMapper;
import learn.data.mappers.ItemMapper;
import learn.data.mappers.ModifiersMapper;
import learn.models.Item;
import learn.models.Modifiers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import learn.models.Category;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ItemJdbcRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;


    public ItemJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> findAll() {
        final String sql = "SELECT " +
                " item_id," +
                "    item_title, " +
                "    item_description, " +
                "    item_price, " +
                "    item_disabled, " +
                "    item_photo, " +
                "    category_id " +
                "FROM item;";

        List<Item> items = jdbcTemplate.query(sql, new ItemMapper());

        for(Item item : items){
            fillFields(item);
        }
        return items;

    }

    @Override
    public Item findById(int itemId) {

        final String sql = "SELECT " +
                " item_id," +
                "    item_title, " +
                "    item_description, " +
                "    item_price, " +
                "    item_disabled, " +
                "    item_photo, " +
                "    category_id " +
                "FROM item " +
                " WHERE item_id = ?; ";

        Item item = jdbcTemplate.query(sql, new ItemMapper(), itemId).stream().findFirst().orElse(null);

        if(item != null) fillFields(item);

        return item;

    }

    @Override
    public List<Item> findByCategoryId(int categoryId) {

        final String sql = "SELECT " +
                " item_id," +
                "    item_title, " +
                "    item_description, " +
                "    item_price, " +
                "    item_disabled, " +
                "    item_photo, " +
                "    category_id " +
                "FROM item " +
                " WHERE category_id = ?; ";

        List<Item> items = jdbcTemplate.query(sql, new ItemMapper(), categoryId);
        for(Item item : items){
            fillFields(item);
        }

        return items;


    }

    @Override
    @Transactional
    public Item add(Item item) {

        final String sql = "INSERT INTO item( "+
                "    item_title," +
                "    item_description," +
                "    item_photo," +
                "    item_price," +
                "    item_disabled," +
                "    category_id)" +
                " VALUES(?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getPhoto());
            ps.setBigDecimal(4, item.getPrice());
            ps.setBoolean(5, item.isDisabled());
            ps.setInt(6, item.getCategory().getCategoryId());

            return ps;
        }, keyHolder);

        if(rowsAffected <= 0){
            return null;
        }

        item.setItemId(keyHolder.getKey().intValue());

        List<Modifiers> modifiers = item.getModifiers();

        for(Modifiers modifier : modifiers){
            boolean expected = addSubModifiers(item.getItemId(), modifier.getModifier_id());
            if(!expected){
                return null;
            }
        }

    return item;

    }

    @Override
    @Transactional
    public boolean update(Item item) {

        final String sql = "UPDATE item SET" +
                " item_title = ? " +
                " item_description = ?" +
                " item_photo = ? " +
                " item_price = ?" +
                " item_disabled = ? +" +
                " category_id = ? " +
                " WHERE item_id = ? ;";

        int rowsAffected = jdbcTemplate.update(sql, item.getTitle(),
                item.getDescription(), item.getPhoto(), item.getPrice(), item.isDisabled(),
                item.getCategory().getCategoryId(), item.getItemId());

        if(rowsAffected <= 0){
            return false;
        }

        //// at this point i need to go through
        // the modifiers to see if anything has changed
        // and update the submodifiers accordingly


        return true;


    }

    @Override
    @Transactional
    public boolean deletebyID(int itemId) {
        return jdbcTemplate.update("DELETE FROM item where item_id = ? ;", itemId) > 0;
    }

    private void fillFields(Item item){
        item.setCategory(findCategory(item));
        item.setModifiers(findModifiersByItemId(item.getItemId()));

    }

    private Category findCategory(Item item){

        final String sql = "SELECT category_id, category_name FROM category WHERE category_id = ?;";

        return jdbcTemplate.query(sql, new CategoryMapper(), item.getCategory().getCategoryId()).stream().findFirst().orElse(null);
    }
    private List<Modifiers> findModifiersByItemId(int itemId){
        final String sql = "SELECT  " +
                " m.modifier_id AS modifier_id, " +
                "    modifier_name, " +
                "    modifier_price, " +
                "    modifier_disabled " +
                "FROM modifiers m " +
                "JOIN submodifier sb ON m.modifier_id = sb.modifier_id " +
                "JOIN item i ON sb.item_id = i.item_id " +
                "WHERE i.item_id = ? ;";

        return jdbcTemplate.query(sql,  new ModifiersMapper(), itemId);
    }


    private boolean addSubModifiers(int itemId, int modifierId){
        final String sql = "INSERT into submodifier(item_id, modifier_id) values( ?, ? );";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, itemId);
            ps.setInt(2, modifierId);
            return ps;

        }, keyHolder);

        return rowsAffected > 0;

    }
}
