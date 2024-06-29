package learn.data;

import learn.data.mappers.CategoryMapper;
import learn.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CategoryJdbcRepository implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Category> findAll() {
       final String sql = "SELECT category_id, category_name FROM category; ";

       return jdbcTemplate.query(sql, new CategoryMapper());
    }

    @Override
    public Category add(Category category) {
        final String sql = "INSERT INTO category(category_name) VALUES ( ? ); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            return ps;

        }, keyHolder);

        if (rowsAffected <= 0){
            return null;
        }

        category.setCategoryId(keyHolder.getKey().intValue());
        return category;


    }

    @Override
    public boolean update(Category category) {
        final String sql = "UPDATE category set category_name = ?" +
                "WHERE category_id = ? ;";

        return jdbcTemplate.update(sql,
                category.getName(),
                category.getCategoryId()
                ) > 0;
    }

    @Override
    public boolean deletedById(int categoryId) {
        return jdbcTemplate.update("DELETE FROM category WHERE category_id = ? ;", categoryId) > 0;
    }
}
