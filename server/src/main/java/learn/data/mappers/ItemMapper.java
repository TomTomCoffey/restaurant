package learn.data.mappers;

import learn.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getInt("item_id"));
        item.setTitle(resultSet.getString("item_title"));
        item.setDescription(resultSet.getString("item_description"));
        item.setPrice(resultSet.getBigDecimal("item_price"));
        item.setPhoto(resultSet.getString("item_photo"));
        item.setDisabled(resultSet.getBoolean("item_disabled"));

        return item;
    }
}
