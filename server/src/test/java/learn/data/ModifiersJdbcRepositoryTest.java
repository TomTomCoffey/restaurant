package learn.data;

import learn.models.Modifiers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ModifiersJdbcRepositoryTest {

    @Autowired
    ModifiersJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindAll(){
        List<Modifiers> modifiersList = repository.findAll();
        assertNotEquals(0, modifiersList.size());
        System.out.println(modifiersList.size());

    }

    @Test
    void shouldFindByItemId(){
        List<Modifiers> modifiersList = repository.findByItemId(1);
        assertNotEquals(0, modifiersList);
        System.out.println(modifiersList);
    }

    @Test
    void shouldAdd(){
        Modifiers modifiers = new Modifiers();
        modifiers.setName("Test");
        modifiers.setPrice(new BigDecimal("100.00"));
        modifiers.setDisabled(true);
        Modifiers expected = repository.add(modifiers);
        assertNotNull(expected);
        assertEquals(expected.getName(), modifiers.getName());
    }

    @Test
    void shouldUpdate(){
        Modifiers m = repository.findAll().stream().findFirst().orElse(null);
        m.setName("TomTom");
        boolean expected = repository.update(m);
        assertTrue(expected);
        m = repository.findAll().stream().findFirst().orElse(null);
        assertEquals("TomTom", m.getName());
    }

    @Test
    void shouldDelete(){
        boolean expected = repository.deleteById(11);
        assertTrue(expected);
        expected = repository.deleteById(9090);
        assertFalse(expected);

    }






}