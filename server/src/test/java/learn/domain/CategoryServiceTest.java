package learn.domain;

import learn.data.CategoryRepository;
import learn.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CategoryServiceTest {

    @Autowired
    CategoryService service;

    @MockBean
    CategoryRepository repository;


    @Test
    void shouldAdd(){
        Category c = new Category(0, "Meats");
        when(repository.findAll()).thenReturn(makeCategories());
        when(repository.add(c)).thenReturn(c);
        Result<Category> result = service.add(c);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(result.getPayload().getName(), c.getName());
    }

    @Test
    void shouldNotAddWithNullValue(){
        when(repository.findAll()).thenReturn(makeCategories());
        when(repository.add(null)).thenReturn(null);
        Result<Category> result = service.add(null);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddCategoryWithNoName(){
        Category c = makeCategory();
        c.setCategoryId(0);
        c.setName(null);
        when(repository.findAll()).thenReturn(makeCategories());
        when(repository.add(c)).thenReturn(c);
        Result<Category> result = service.add(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }
    @Test
    void shouldNotAddCategoryWithEmptyName(){
        Category c = makeCategory();
        c.setCategoryId(0);
        c.setName("   ");
        when(repository.findAll()).thenReturn(makeCategories());
        when(repository.add(c)).thenReturn(c);
        Result<Category> result = service.add(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddCategoryWithSetId(){
        Category c = makeCategory();
        when(repository.findAll()).thenReturn(makeCategories());
        when(repository.add(c)).thenReturn(c);
        Result<Category> result = service.add(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldUpdate(){
        Category c = makeCategory();
        c.setName("Testing");
        when(repository.update(c)).thenReturn(true);
        when(repository.findAll()).thenReturn(makeCategories());
        Result<Category> result = service.update(c);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWithBadID(){
        Category c = makeCategory();
        c.setCategoryId(99);
        c.setName("Testing");
        when(repository.update(c)).thenReturn(true);
        when(repository.findAll()).thenReturn(makeCategories());
        Result<Category> result = service.update(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotUpdateWithSameNameAsOtherCategory(){
        Category c = makeCategory();
        c.setName("Sandwiches");
        when(repository.update(c)).thenReturn(true);
        when(repository.findAll()).thenReturn(makeCategories());
        Result<Category> result = service.update(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWithNullName(){
        Category c = makeCategory();
        c.setName(null);
        when(repository.update(c)).thenReturn(true);
        when(repository.findAll()).thenReturn(makeCategories());
        Result<Category> result = service.update(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotUpdateWithEmptyName(){
        Category c = makeCategory();
        c.setName("    ");
        when(repository.update(c)).thenReturn(true);
        when(repository.findAll()).thenReturn(makeCategories());
        Result<Category> result = service.update(c);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
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
        System.out.println(categoryList);
        return categoryList;
    }
}