package learn.controllers;


import learn.domain.ItemService;
import learn.domain.RevenueService;
import learn.models.Item;
import learn.models.Revenue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/revenue")
public class RevenueController {

    final RevenueService service;
    final ItemService itemService;

    public RevenueController(RevenueService service, ItemService itemService) {
        this.service = service;
        this.itemService = itemService;
    }


    @GetMapping
    public Map<String, List<Object>> getRevenue(){
        Map<String, List<Object>> map = new HashMap<>();
        List<Revenue> revenues = service.getWeeklyRevenue();
        List<Item> getTop5Items = itemService.findTop5Rank();
        map.put("revenue", Collections.singletonList(revenues));
        map.put("items", Collections.singletonList(getTop5Items));

        return map;

    }
}
