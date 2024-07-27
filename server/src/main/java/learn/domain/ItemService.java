package learn.domain;

import learn.data.CategoryRepository;
import learn.data.ItemRepository;
import learn.data.ModifiersRepository;
import learn.models.Category;
import learn.models.Item;
import learn.models.Modifiers;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final CategoryRepository categoryRepository;
    private final ModifiersRepository modifiersRepository;

    public ItemService(ItemRepository repository, CategoryRepository categoryRepository, ModifiersRepository modifiersRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.modifiersRepository = modifiersRepository;
    }

    public List<Item> findAll(){
        return repository.findAll();
    }

    public Item findById(int id){
        return repository.findById(id);
    }

    public List<Item> findByCategoryId(int id){
        return repository.findByCategoryId(id);
    }

    public Result<Item> add(Item item){
        Result<Item> result = validate(item);

        if(!result.isSuccess()){
            return result;
        }

        if(item.getItemId() != 0){
            result.addMessage("New items cannot have a specific ID", ResultType.INVALID);
            return result;
        }

        Item i = repository.add(item);

        if(i == null){
            result.addMessage("An error has occused in the repository layer", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(i);

        return result;


    }

    public Result<Item> update(Item item){

        Result<Item> result = validate(item);

        if(!result.isSuccess()){
            return result;
        }

        Item checker = repository.findById(item.getItemId());

        if(checker == null){
            result.addMessage("Item not found in database to update", ResultType.NOT_FOUND);
            return result;
        }

        boolean excpected = repository.update(item);

        if(!excpected){
            result.addMessage("An error has occured in repository", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(item);

        return result;

    }

    public boolean deleteById(int id){
        return repository.deletebyID(id);
    }

    public Result<Item> disableCategory(int categoryId){
        Result<Item> result = new Result<>();

        Category category = categoryRepository.findAll().stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst().orElse(null);

        if(category == null){
            result.addMessage("Category does not exist", ResultType.NOT_FOUND);
            return result;
        }
        boolean expected = repository.disableByCategory(categoryId);

        if(!expected){
            result.addMessage("Something went wrong in the database", ResultType.INVALID);
            return result;
        }
        return result;

    }
    public Result<Item> enableCategory(int categoryId){
        Result<Item> result = new Result<>();

        Category category = categoryRepository.findAll().stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst().orElse(null);

        if(category == null){
            result.addMessage("Category does not exist", ResultType.NOT_FOUND);
            return result;
        }
        boolean expected = repository.enableByCategory(categoryId);

        if(!expected){
            result.addMessage("Something went wrong in the database", ResultType.INVALID);
            return result;
        }
        return result;
    }

    private Result<Item> validate(Item item){
        Result<Item> result = new Result<>();
        if(item == null){
            result.addMessage("Item cannot be null", ResultType.INVALID);
            return result;
        }

        if(item.getTitle() == null || item.getTitle().isEmpty() || item.getTitle().isBlank()){
            result.addMessage("Item must have a valid title", ResultType.INVALID);
        }

        if(item.getDescription() == null || item.getDescription().isEmpty() || item.getDescription().isBlank()){
            result.addMessage("Item must have a valid description", ResultType.INVALID);
        }

        if(item.getPrice().doubleValue() <= 0.00){
            result.addMessage("Item must have a valid price", ResultType.INVALID);
        }

        Item checker = repository.findAll().stream()
                .filter(i -> i.getTitle().equals(item.getTitle()) && i.getItemId() != item.getItemId())
                .findFirst().orElse(null);

        if(checker != null){
            result.addMessage("Item already exists with that title", ResultType.INVALID);
        }

        if(item.getCategory() == null){
            result.addMessage("Item must be in a category", ResultType.NOT_FOUND);
            return result;
        }
        Category category = categoryRepository.findAll().stream()
                .filter(c -> c.getCategoryId() == item.getCategory().getCategoryId())
                .findFirst().orElse(null);

        if(category == null){
            result.addMessage("Must be a valid category", ResultType.INVALID);
        }

        HashSet<Integer> modifiers = new HashSet<>();

        List<Modifiers> lister = modifiersRepository.findAll();

        for(Modifiers m : lister){
            modifiers.add(m.getModifier_id());
        }

        for(Modifiers m : item.getModifiers()){
            if(!modifiers.contains(m.getModifier_id())){
                result.addMessage("Error: Modifier with ID " + m.getModifier_id() +" does not exist", ResultType.INVALID);
                return result;
            }
        }

        return result;
    }
}
