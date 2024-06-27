package learn.data;

import learn.models.Category;

import java.util.List;

public interface CategoryRepository {

    public List<Category> findAll();

    public Category add(Category category);

    public boolean update(Category category);

    public boolean deletedById( int categoryId);
}
