package learn.domain;

import learn.data.CategoryRepository;
import learn.models.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;

    }


}
