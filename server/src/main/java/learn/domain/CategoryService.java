package learn.domain;

import learn.data.CategoryRepository;
import learn.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;

    }

    public List<Category> findAll(){
        return repository.findAll();
    }



    public Result<Category> add(Category category){

        Result<Category> result = new Result<>();

        if(category.getName() == null || category.getName().isEmpty() || category.getName().isBlank()){
            result.addMessage("Category must have valid name", ResultType.INVALID);
            return result;
        }
        Category category1 = repository.add(category);

        if(category1 == null){
            result.addMessage("Error when adding Category to database", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(category);

        return result;


    }

    public Result<Category> update(Category category){
        Result<Category> result = new Result<>();

        if(category.getName() == null || category.getName().isEmpty() || category.getName().isBlank()){
            result.addMessage("Category must have valid name", ResultType.INVALID);
            return result;
        }

        boolean expected = repository.update(category);

        if(!expected){
            result.addMessage("An error has occured please try updating again", ResultType.INVALID);
            return result;
        }

        result.setPayload(category);
        return result;

    }

    public boolean deleteById(int id){
        return repository.deletedById(id);
    }


}
