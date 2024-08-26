package learn.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private User user;
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private BigDecimal cost;

    public Order() {
    }

    public Order(User user, List<OrderItem> items, LocalDateTime orderTime, BigDecimal cost) {
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
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

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", items=" + items +
                ", orderTime=" + orderTime +
                ", cost=" + cost +
                '}';
    }
}