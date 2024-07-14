package learn.domain;

import learn.data.CategoryRepository;
import learn.data.ItemRepository;
import learn.data.ModifiersRepository;
import learn.models.Category;
import learn.models.Item;
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
class ItemServiceTest {

    @Autowired
    ItemService service;

    @MockBean
    ItemRepository repository;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    ModifiersRepository modifiersRepository;


    @Test
    void shouldAdd(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        item.setTitle("Ham");
        Result<Item> result = service.add(item);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithSameName(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }
    @Test
    void shouldNotAddWithBadPrice(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        item.setTitle("Testing++");
        item.setPrice(new BigDecimal("0.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    @Test
    void shouldNotAddWithoutTitle(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        item.setTitle("     ");
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }
    @Test
    void shouldNotAddWithBadCategory(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        item.setCategory(null);
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }
    @Test
    void shouldNotAddWithBadModifier(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItem();
        when(repository.add(item)).thenReturn(item);
        item.setItemId(0);
        Modifiers m = getModifier();
        m.setModifier_id(898);
        item.getModifiers().add(m);
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldUpdateTitle(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        when(repository.findById(item.getItemId())).thenReturn(item);
        item.setTitle("Hello");
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdateDescription(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        when(repository.findById(item.getItemId())).thenReturn(item);
        item.setDescription("Hello World");
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldUpdatePrice(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        when(repository.findById(item.getItemId())).thenReturn(item);
        item.setPrice(new BigDecimal("5.55"));
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }
    @Test
    void shouldNotUpdateWithBadID(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        item.setItemId(909);
        item.setPrice(new BigDecimal("5.55"));
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithBadPrice(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        when(repository.findById(item.getItemId())).thenReturn(item);
        item.setPrice(new BigDecimal("-5.55"));
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWithBadTitle(){
        when(repository.findAll()).thenReturn(makeItems());
        when(categoryRepository.findAll()).thenReturn(makeCategories());
        when(modifiersRepository.findAll()).thenReturn(getModifiers());
        Item item = makeItems().get(0);
        when(repository.findById(item.getItemId())).thenReturn(item);
        item.setTitle(null);
        when(repository.update(item)).thenReturn(true);
        Result<Item> result = service.update(item);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }


    Item makeItem(){
        return new Item(1, "Glizzy", "glizzy", new BigDecimal("2.75"), "temp.jgp", makeCategory(), getModifiers(), false);
    }
    List<Item> makeItems(){
        List<Item> items = new ArrayList<>();
        items.add(makeItem());
        Item item  = makeItem();
        item.setItemId(2);
        item.setTitle("Chezeburgerz");
        item.setDescription("burger");
        items.add(item);
        Item item2 = makeItem();
        item2.setItemId(3);
        item2.getModifiers().remove(0);
        item2.setTitle("BEC");
        item2.setDescription("Brekky");
        items.add(item2);
        return items;

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
    Category makeCategory(){
        Category c = new Category(1, "Breakfast");
        return c;
    }
    List<Category> makeCategories(){
        List<Category> categoryList = new ArrayList<>();
        Category c = makeCategory();
        categoryList.add(c);
        Category d = makeCategory();
        d.setCategoryId(2);
        d.setName("Hot Dogs, Burgers");
        categoryList.add(d);
        Category e = makeCategory();
        e.setCategoryId(3);
        e.setName("Sandwiches");
        categoryList.add(e);
        return categoryList;
    }

}