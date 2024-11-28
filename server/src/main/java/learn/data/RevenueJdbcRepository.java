package learn.data;

import learn.data.mappers.RevenueMapper;
import learn.models.Revenue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RevenueJdbcRepository implements RevenueRepository {

    private  final JdbcTemplate jdbcTemplate;

    public RevenueJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Revenue> getWeeklyRevenue() {
        final String sql = "SELECT " +
                " SUM(revenue_in) as revenue_in, " +
                "    revenue_day " +
                "FROM revenue " +
                "GROUP BY revenue_day " +
                "LIMIT 7;";
        return jdbcTemplate.query(sql, new RevenueMapper());
    }

    @Override
    @Transactional
    public Revenue add(Revenue revenue) {
        final String sql = "INSERT INTO revenue(revenue_in, revenue_day) values(?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, revenue.getRevenue());
            ps.setDate(2, java.sql.Date.valueOf(revenue.getDate()));

            return ps;

        }, keyHolder);

        if(rowsAffected <= 0){
            return null;
        }

        return revenue;

    }


    @Override
    public boolean deleteByDate(LocalDate date) {
        final String sql = "DELETE FROM revenue WHERE revenue_day < ?;";

        return false;
    }
}
