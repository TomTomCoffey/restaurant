package learn.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private User user;
    private List<Item> items;
    private LocalDateTime orderTime;
    private BigDecimal cost;

    public Order(){}

    public Order(User user, List<Item> items, LocalDateTime orderTime, BigDecimal cost) {
        this.user = user;
        this.items = items;
        this.orderTime = orderTime;
        this.cost = cost;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


}
