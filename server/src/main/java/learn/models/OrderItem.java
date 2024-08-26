package learn.models;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {

    private String item;
    private List<Modifiers> modifiers;
    private int quantity;
    private BigDecimal total;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<Modifiers> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifiers> modifiers) {
        this.modifiers = modifiers;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item='" + item + '\'' +
                ", modifiers=" + modifiers +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}