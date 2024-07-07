package learn.data;

import learn.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcRepositoryTest {


    @Autowired
    UserJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void shouldFindAll(){
        List<User> users = repository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void shouldFindById(){
        User user = repository.findById(1);
        assertNotNull(user);
        assertEquals("MrTony", user.getUserName());
    }

    @Test
    void shouldFindBYUsername(){
        User user = repository.findByUserName("TomTom");
        assertNotNull(user);
        assertEquals(2, user.getUserId());
    }

    @Test
    void shouldAdd(){
        User user = repository.findById(1);
        user.setUserId(0);
        user.setUserName("Testing");
        User expected = repository.add(user);
        assertNotNull(expected);
        assertEquals("Testing", expected.getUserName());
    }

    @Test
    void shouldUpdate(){
        User user = repository.findById(1);
        user.setUserName("T-Diddy");
        boolean expected = repository.update(user);
        assertTrue(expected);
        user = repository.findById(1);
        assertEquals("T-Diddy", user.getUserName());

    }

    @Test
    void shouldDelete(){
        boolean expected = repository.deleteById(3);
        assertTrue(expected);
        expected = repository.deleteById(909);
        assertFalse(expected);
    }

}