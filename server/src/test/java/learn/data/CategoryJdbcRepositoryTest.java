package learn.data;

import learn.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CategoryJdbcRepositoryTest {

    @Autowired
    CategoryJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}


    @Test
    void shouldFindAll(){
        List<Category> categoryList = repository.findAll();
        assertNotEquals(0, categoryList);
        assertEquals(5, categoryList.size());
    }

    @Test
    void shouldAdd(){
        Category category = new Category();
        category.setCategoryId(0);
        category.setName("Testing");
        Category expected = repository.add(category);
        assertEquals(category.getName(), expected.getName());
    }

    @Test
    void shouldUpdate(){
        Category c = repository.findAll().stream().findFirst().orElse(null);
        c.setName("Testing");
        boolean expected = repository.update(c);
        assertTrue(expected);
        c = repository.findAll().stream().findFirst().orElse(null);
        assertEquals("Testing", c.getName());

    }

    @Test
    void shouldDelete(){
        boolean expected = repository.deletedById(6);
        assertTrue(expected);
    }

}