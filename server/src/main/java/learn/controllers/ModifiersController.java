package learn.controllers;

import learn.domain.ModifiersService;
import learn.domain.Result;
import learn.models.Modifiers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/modifiers")
public class ModifiersController {

    final ModifiersService service;


    public ModifiersController(ModifiersService service) {
        this.service = service;
    }

    @GetMapping
    public List<Modifiers> findAll(){
        return service.findAll();
    }

   @GetMapping("/item/{id}")
    public ResponseEntity<List<Modifiers>> findByItemId(@PathVariable int id){
        List<Modifiers> modifiers = service.findByItemId(id);
        if(!modifiers.isEmpty()){
            return ResponseEntity.ok(modifiers);
        }
        return ResponseEntity.notFound().build();

   }

   @PostMapping
    public ResponseEntity<Object> add(@RequestBody Modifiers modifiers){
        Result<Modifiers> result = service.add(modifiers);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
   }

   @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Modifiers modifiers){
        if(id != modifiers.getModifier_id()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Modifiers> result = service.update(modifiers);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable int id){

        if(service.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


   }

}
