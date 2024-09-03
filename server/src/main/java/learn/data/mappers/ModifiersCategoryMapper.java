package learn.data.mappers;

import learn.models.ModifiersCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifiersCategoryMapper implements RowMapper<ModifiersCategory> {
    @Override
    public ModifiersCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        ModifiersCategory modifiersCategory = new ModifiersCategory();
        modifiersCategory.setId(resultSet.getInt("category_modifiers_id"));
        modifiersCategory.setName(resultSet.getString("category_modifiers_title"));
        modifiersCategory.setRequired(resultSet.getBoolean("category_modifiers_required"));

        return modifiersCategory;
    }
}
