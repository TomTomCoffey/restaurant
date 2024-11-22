package learn.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Revenue {

    private BigDecimal revenue;
    private LocalDate date;

    public Revenue(BigDecimal revenue, LocalDate date) {
        this.revenue = revenue;
        this.date = date;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
