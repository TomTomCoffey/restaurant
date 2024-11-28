package learn.controllers;


import learn.domain.RevenueService;
import learn.models.Revenue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/revenue")
public class RevenueController {

    final RevenueService service;

    public RevenueController(RevenueService service) {
        this.service = service;
    }


    @GetMapping
    public List<Revenue> getWeeklyRevenue(){
        return service.getWeeklyRevenue();

    }
}
