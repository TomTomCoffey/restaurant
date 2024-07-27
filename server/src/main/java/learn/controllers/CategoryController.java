package learn.controllers;


import learn.domain.CategoryService;
import learn.domain.Result;
import learn.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/category")
public class CategoryController {

    final CategoryService service;


    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Category category){
        Result<Category> result = service.add(category);

        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Category category){
        if(id != category.getCategoryId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Category> result = service.update(category);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if(service.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
