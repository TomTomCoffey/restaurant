package learn.data;

import learn.data.mappers.UserMapper;
import learn.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository {

      private final JdbcTemplate jdbcTemplate;


    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAll() {
        final String sql = "SELECT " +
                " user_id, " +
                " user_first_name," +
                " user_last_name, " +
                " user_username, " +
                " user_email, " +
                " user_hashed_password, " +
                " user_is_banned " +
                " FROM app_user;";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        for (User user : users){
            user.setRoles(findRoles(user.getUserId()));
        }

        return users;


    }

    @Override
    public User findByUserName(String userName) {
        final String sql = "SELECT " +
                " user_id, " +
                " user_first_name," +
                " user_last_name, " +
                " user_username, " +
                " user_email, " +
                " user_hashed_password, " +
                " user_is_banned " +
                " FROM app_user " +
                " WHERE user_username = ?; ";

        User user = (User) jdbcTemplate.query(sql, new UserMapper(), userName)
                .stream()
                .findFirst()
                .orElse(null);

        if(user != null) {
            user.setRoles(findRoles(user.getUserId()));
        }

        return user;


    }

    @Override
    public User findById(int userId) {
        final String sql = "SELECT " +
                " user_id, " +
                " user_first_name," +
                " user_last_name, " +
                " user_username, " +
                " user_email, " +
                " user_hashed_password, " +
                " user_is_banned " +
                " FROM app_user " +
                " WHERE user_id = ?; ";

        User user = (User) jdbcTemplate.query(sql, new UserMapper(), userId)
                .stream()
                .findFirst()
                .orElse(null);

        if(user != null) {
            user.setRoles(findRoles(user.getUserId()));
        }

        return user;
    }

    @Override
    @Transactional
    public User add(User user) {
        final String sql = "INSERT INTO app_user(" +
                " user_first_name, " +
                " user_last_name, " +
                " user_username, " +
                " user_hashed_password, " +
                " user_email ) " +
                " VALUES(?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getHashedPassword());
            ps.setString(5, user.getEmail());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0){
            return null;
        }
        user.setUserId(keyHolder.getKey().intValue());
        updateRoles(user);

        return user;
    }

    @Override
    @Transactional
    public boolean update(User user) {

        final String sql = "UPDATE app_user SET" +
                " user_username = ?, " +
                " user_first_name = ?, " +
                " user_last_name = ?, " +
                " user_hashed_password = ?," +
                " user_is_banned = ?, " +
                " user_email = ?" +
                " WHERE user_id = ?; ";

        return jdbcTemplate.update(sql, user.getUserName(),
                user.getFirstName(), user.getLastName(), user.getHashedPassword(),
                user.isBanned(), user.getEmail(), user.getUserId()) > 0;

    }

    @Override
    @Transactional
    public boolean deleteById(int userId) {
        jdbcTemplate.update("delete from user_role where user_id = ?;", userId);
        return jdbcTemplate.update("DELETE FROM app_user WHERE user_id = ? ;", userId) > 0;
    }

    private List<String> findRoles(int userID){
        final String sql = "select r.role_name " +
                "from app_role r " +
                "where r.role_id in ( " +
                "select u.role_id from user_role u " +
                "where u.user_id = ?);";

        return jdbcTemplate.queryForList(sql, String.class, userID);
    }

    private void updateRoles(User user){
        jdbcTemplate.update("delete from user_role where user_id = ?;", user.getUserId());

        for (String role : user.getRoles()){
            String sql = "insert into user_role (user_id, role_id) " +
                    "select ?, role_id from app_role where role_name = ?";

            jdbcTemplate.update(sql, user.getUserId(), role);
        }
    }
}
