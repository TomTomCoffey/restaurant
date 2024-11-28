package learn.data.mappers;

import learn.models.Revenue;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RevenueMapper implements RowMapper<Revenue> {
    @Override
    public Revenue mapRow(ResultSet resultSet, int i) throws SQLException {
        Revenue revenue = new Revenue();
        revenue.setRevenue(resultSet.getBigDecimal("revenue_in"));
        revenue.setDate(resultSet.getDate("revenue_day").toLocalDate());
        return revenue;

    }
}
