package learn.domain;

import learn.data.UserRepository;
import learn.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldAdd(){
        User user = makeUser();
        user.setUserId(0);
        user.setUserName("Timmy");
        user.setEmail("Timmy@email.com");
        user.setHashedPassword("H33lloWord!");
        when(repository.findByUserName(user.getUserName())).thenReturn(null);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.add(user)).thenReturn(user);
        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldNotAddWithBadPassword(){
        User user = makeUser();
        user.setUserId(0);
        user.setUserName("Timmy");
        user.setEmail("Timmy@email.com");
        user.setHashedPassword("mot");
        when(repository.findByUserName(user.getUserName())).thenReturn(null);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.add(user)).thenReturn(user);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddWithDupeEmail(){
        User user = makeUser();
        user.setUserId(0);
        user.setUserName("Timmy");
        user.setHashedPassword("H33loWorld!");
        when(repository.findByUserName(user.getUserName())).thenReturn(null);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.add(user)).thenReturn(user);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddWithDupeUsername(){
        User user = makeUser();
        user.setUserId(0);
        user.setEmail("billy@something.com");
        user.setHashedPassword("H33loWorld!");
        when(repository.findByUserName(user.getUserName())).thenReturn(makeUser());
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.add(user)).thenReturn(user);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldUpdateEverything(){
        User user = makeUsers().get(0);
        user.setEmail("billy@something.com");
        user.setHashedPassword("H33loWorld!");
        user.setUserName("Taters");
        when(repository.findByUserName(user.getUserName())).thenReturn(null);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(user);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdateEmailAndPassword(){
        User user = makeUsers().get(0);
        user.setEmail("billy@something.com");
        user.setHashedPassword("H33loWorld!");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(user);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdatePassword(){
        User user = makeUsers().get(0);
        user.setHashedPassword("H33loWorld!");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(user);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdateUserName(){
        User user = makeUsers().get(0);
        user.setUserName("Mamma Mia");
        when(repository.findByUserName(user.getUserName())).thenReturn(null);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(user);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdateName(){
        User user = makeUsers().get(0);
        user.setLastName("pops");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(user);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithBadID(){
        User user = makeUsers().get(0);
        user.setUserId(909);
        user.setLastName("pops");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(null);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateToBadPassword(){
        User user = makeUsers().get(0);
        user.setHashedPassword("pops");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(null);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateToTakenEmail(){
        User user = makeUsers().get(0);
        user.setEmail("Billy@yahoo.com");
        when(repository.findByUserName(user.getUserName())).thenReturn(user);
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(null);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithTakenUserName(){
        User user = makeUsers().get(0);
        user.setUserName("BillyBob");
        when(repository.findByUserName(user.getUserName())).thenReturn(makeUsers().get(1));
        when(repository.findAll()).thenReturn(makeUsers());
        when(repository.findById(user.getUserId())).thenReturn(null);
        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }







    User makeUser(){
        return new User(1, "TomTom " ,"Tom", "Coffey", "Tom@Email.com", "jkjkjkjk182Apl!",  new ArrayList<>(), false);
    }
    List<User> makeUsers(){
        List<User> users = new ArrayList<>();
        users.add(makeUser());
        User u = makeUser();
        u.setUserId(2);
        u.setUserName("BillyBob");
        u.setEmail("Billy@yahoo.com");
        users.add(u);
        return users;

    }

}