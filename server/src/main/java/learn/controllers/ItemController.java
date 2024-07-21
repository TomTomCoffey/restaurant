package learn.controllers;


import learn.domain.ItemService;
import learn.models.Item;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
