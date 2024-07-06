package learn.data;

import learn.models.Category;
import learn.models.Item;

import java.util.List;

public interface ItemRepository {

    public List<Item> findAll();

    public Item findById(int itemId);

    public List<Item> findByCategoryId(int categoryId);

    public Item add(Item item);

    public boolean update(Item item);

    public boolean deletebyID(int itemId);
}
