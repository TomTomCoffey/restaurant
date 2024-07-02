package learn.data;

import learn.data.mappers.ModifiersMapper;
import learn.models.Modifiers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ModifiersJdbcRepository implements ModifiersRepository {

    private final JdbcTemplate jdbcTemplate;


    public ModifiersJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Modifiers> findAll() {
        final String sql = "SELECT " +
                " modifier_id, " +
                "    modifier_name, " +
                "    modifier_price, " +
                "    modifier_disabled " +
                "FROM modifiers;" ;

        return jdbcTemplate.query(sql, new ModifiersMapper());

    }

    @Override
    public List<Modifiers> findByItemId(int itemId) {
        final String sql = "SELECT  " +
                " m.modifier_id AS modifier_id, " +
                "    modifier_name, " +
                "    modifier_price, " +
                "    modifier_disabled " +
                "FROM modifiers m " +
                "JOIN submodifier sb ON m.modifier_id = sb.modifier_id " +
                "JOIN item i ON sb.item_id = i.item_id " +
                "WHERE i.item_id = 3;";

        return jdbcTemplate.query(sql,  new ModifiersMapper());
    }

    @Override
    public Modifiers add(Modifiers modifiers) {
        final String sql = "INSERT INTO modifiers(modifier_name, modifier_price, modifier_disabled)" +
                " values(?, ?, ? );";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, modifiers.getName());
            ps.setBigDecimal(2, modifiers.getPrice());
            ps.setBoolean(3, modifiers.isDisabled());
            return ps;


        }, keyHolder);

        if(rowsAffected <= 0){
            return null;
        }
        modifiers.setModifier_id(keyHolder.getKey().intValue());
        return modifiers;


    }

    @Override
    public boolean update(Modifiers modifiers) {
        final String sql = "UPDATE modifiers SET " +
                " modifier_name = ? " +
                " modifier_price = ? " +
                " modifier_disabled = ? " +
                " WHERE modifier_id = ?; ";

         return jdbcTemplate.update(sql, modifiers.getName(),
                 modifiers.getPrice().doubleValue(), modifiers.isDisabled(), modifiers.getModifier_id()) > 0;
    }

    @Override
    public boolean deleteById(int modifiersId) {
        return jdbcTemplate.update("DELETE FROM modifiers WHERE modifier_id = ?;", modifiersId) > 0;
    }
}
