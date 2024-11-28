package learn.data;

import learn.models.Revenue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository {

    public List<Revenue> getWeeklyRevenue();

    public Revenue add(Revenue revenue);

    public boolean deleteByDate(LocalDate date);

}
