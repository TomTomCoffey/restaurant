package learn.controllers;

import learn.domain.Result;
import learn.domain.UserService;
import learn.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/user")
public class UserController {

    final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        User user = service.findByUserId(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username){
        User user = service.findByUsername(username);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody User user){
        Result<User> result = service.add(user);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable int userId, @RequestBody User user){
        if(userId != user.getUserId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<User> result = service.update(user);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId){
        if(service.deleteById(userId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
