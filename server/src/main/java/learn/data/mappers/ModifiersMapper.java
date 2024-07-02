package learn.data.mappers;

import learn.models.Modifiers;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifiersMapper implements RowMapper <Modifiers> {
    @Override
    public Modifiers mapRow(ResultSet resultSet, int i) throws SQLException {
        Modifiers modifiers = new Modifiers();
        modifiers.setModifier_id(resultSet.getInt("modifier_id"));
        modifiers.setName(resultSet.getString("modifier_name"));
        modifiers.setPrice(new BigDecimal(resultSet.getString("modifier_price")));
        modifiers.setDisabled(resultSet.getBoolean("modifier_disabled"));
        return modifiers;
    }
}
