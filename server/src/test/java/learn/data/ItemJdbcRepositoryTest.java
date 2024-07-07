package learn.data;

import learn.models.Category;
import learn.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemJdbcRepositoryTest {

    @Autowired
    ItemJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindAll(){
        List<Item> itemList = repository.findAll();
        System.out.println(itemList);
        assertNotEquals(itemList, 0);
        System.out.println(itemList);
    }

    @Test
    void shouldFindByCategoryId(){
        Category category = new Category();
        category.setCategoryId(1);
        List<Item> items = repository.findByCategoryId(category.getCategoryId());
        assertNotEquals(0, items.size());

    }

    @Test
    void shouldAdd(){
        Item item = new Item();
        Item mods = repository.findById(1);
        item.setItemId(0);
        item.setTitle("Hey Whats up Test");
        item.setDisabled(false);
        item.setModifiers(new ArrayList<>());
        item.setCategory(new Category());
        item.getCategory().setCategoryId(1);
        item.setPrice(new BigDecimal("100.00"));
        item.setDescription("testing");
        item.setPhoto("test.jsp");
        item.setModifiers(mods.getModifiers());
        Item expected = repository.add(item);
        assertEquals(item.getTitle(), expected.getTitle());
        Item checker = repository.findById(expected.getItemId());
        assertEquals(mods.getModifiers().size(), checker.getModifiers().size());
    }

    @Test
    void shouldDelete(){
        Item item = new Item();
        Item mods = repository.findById(1);
        item.setItemId(0);
        item.setTitle("Hey Whats up Test");
        item.setDisabled(false);
        item.setModifiers(new ArrayList<>());
        item.setCategory(new Category());
        item.getCategory().setCategoryId(1);
        item.setPrice(new BigDecimal("100.00"));
        item.setDescription("testing");
        item.setPhoto("test.jsp");
        Item expected = repository.add(item);
        boolean whatWhats = repository.deletebyID(expected.getItemId());
        assertTrue(whatWhats);
        whatWhats = repository.deletebyID(909);
        assertFalse(whatWhats);

    }

    @Test
    void shouldUpdate(){
        Item item = repository.findById(1);
        item.setTitle("Testing");
        int modSize = item.getModifiers().size();
        item.getModifiers().remove(0);
        boolean expected = repository.update(item);
        assertTrue(expected);
        item = repository.findById(1);
        assertEquals("Testing", item.getTitle());
        assertEquals(modSize-1, item.getModifiers().size());

    }




}