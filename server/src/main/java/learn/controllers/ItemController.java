package learn.controllers;


import learn.domain.ItemService;
import learn.domain.Result;
import learn.models.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/item")
public class ItemController {

    final ItemService service;


    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> findAll(){
        return service.findAll();
    }

    @GetMapping("/id/{itemId}")
    public ResponseEntity<Item> findById(@PathVariable int itemId){
        Item item = service.findById(itemId);
        if( item != null){
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/id/{id}")
    public ResponseEntity<List<Item>> findByCategoryId(@PathVariable int id){
        List<Item> items = service.findByCategoryId(id);
        if(!items.isEmpty()){
            return ResponseEntity.ok(items);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Item item){
        Result<Item> result = service.add(item);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/id/{itemId}")
    public ResponseEntity<Object> update(@PathVariable int itemId, @RequestBody Item item){
        if(itemId != item.getItemId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Item> result = service.update(item);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteById(@PathVariable int itemId){
        if(service.deleteById(itemId)){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
