package learn.domain;

import learn.data.ModifiersRepository;
import learn.models.Modifiers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ModifiersServiceTest {

    @Autowired
    ModifiersService service;

    @MockBean
    ModifiersRepository repository;

    @Test
    void shouldAdd(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        m.setName("Avacado");
        m.setPrice(new BigDecimal("1.50"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }
    @Test
    void shouldAddWithSameNameDifferentPrice(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        m.setPrice(new BigDecimal("0.00"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }

    @Test
    void shouldNotAddDuplicate(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddWithNullName(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        m.setName(null);
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithEmptyName(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        m.setName("     ");
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddWithNegativePrice(){
        Modifiers m = getModifier();
        m.setModifier_id(0);
        m.setPrice(new BigDecimal("-1.00"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.add(m)).thenReturn(m);
        Result<Modifiers> result = service.add(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldUpdate(){
        Modifiers m = getModifier();
        m.setName("Testing");
        m.setPrice(new BigDecimal("5.00"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }
    @Test
    void shouldUpdateSameNameDifferentPrice(){
        Modifiers m = getModifier();
        m.setName("Cheese");
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }

    @Test
    void shouldUpdateWithZeroPrice(){
        Modifiers m = getModifier();
        m.setPrice(new BigDecimal("0.00"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }
    @Test
    void shouldNotUpdateDuplicate(){
        Modifiers m = getModifier();
        m.setPrice(new BigDecimal("0.50"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateModWithBadID(){
        Modifiers m = getModifier();
         m.setName("Testing");
         m.setModifier_id(909);
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithNonValidName(){
        Modifiers m = getModifier();
        m.setName("    ");
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithNegativePrice(){
        Modifiers m = getModifier();
        m.setPrice(new BigDecimal("-5.00"));
        when(repository.findAll()).thenReturn(getModifiers());
        when(repository.update(m)).thenReturn(true);
        Result<Modifiers> result = service.update(m);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }



    Modifiers getModifier(){
        return new Modifiers(1, "Bacon", new BigDecimal("2.00"), false);
    }
    List<Modifiers> getModifiers(){
        List<Modifiers> modifiersList = new ArrayList<>();
        modifiersList.add(getModifier());
        Modifiers m = getModifier();
        m.setModifier_id(2);
        m.setName("Bacon");
        m.setPrice(new BigDecimal("0.50"));
        modifiersList.add(m);
        Modifiers n = getModifier();
        n.setModifier_id(3);
        n.setName("Cheese");
        n.setPrice(new BigDecimal("1.00"));
        modifiersList.add(n);
        Modifiers p = getModifier();
        p.setModifier_id(4);
        p.setPrice(new BigDecimal("0.00"));
        p.setName("Sauce");
        modifiersList.add(p);
        return modifiersList;

    }

}