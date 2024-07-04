package learn.data;

import learn.data.mappers.CategoryMapper;
import learn.data.mappers.ItemMapper;
import learn.data.mappers.ModifiersMapper;
import learn.models.Item;
import learn.models.Modifiers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import learn.models.Category;

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
        fillFields(item);

        return item;

    }

    @Override
    public List<Item> findByCategory(Category category) {

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

        List<Item> items = jdbcTemplate.query(sql, new ItemMapper(), category.getCategoryId());
        for(Item item : items){
            fillFields(item);
        }

        return items;

    }

    @Override
    public Item add(Item item) {





    return null;

    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public boolean deletebyID(int itemId) {
        return false;
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
    private void addSubModifiers(int itemId, int modifierId){
        final String sql = "INSERT into submodifier(item_id, modifier_id) values( ?, ? );";





    }
}
