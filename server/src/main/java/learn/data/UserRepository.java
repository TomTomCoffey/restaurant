package learn.data;

import learn.models.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();

    public User findByUserName(String userName);

    public User findById(int userId);

    public User add(User user);

    public boolean update(User user);

    public boolean deleteById(int userId);
}
