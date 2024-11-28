package learn.domain;

import learn.data.RevenueRepository;
import learn.models.Revenue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    private final RevenueRepository repository;


    public RevenueService(RevenueRepository repository) {
        this.repository = repository;
    }

   public List<Revenue> getWeeklyRevenue(){

        return repository.getWeeklyRevenue();
   }
}
