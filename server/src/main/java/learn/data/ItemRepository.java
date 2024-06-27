package learn.data;

import learn.models.Category;
import learn.models.Item;

import java.util.List;

public interface ItemRepository {

    public List<Item> findAll();

    public Item findById();

    public List<Item> findByCategory();

    public Item add(Item item);

    public boolean update(Item item);

    public boolean deletebyID(int itemId);
}
