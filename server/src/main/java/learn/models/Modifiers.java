package learn.models;

import java.math.BigDecimal;

public class Modifiers {
    private int modifier_id;
    private String name;
    private BigDecimal price;
    private boolean disabled;


    public Modifiers(){}

    public Modifiers(int modifier_id, String name, BigDecimal price, boolean disabled) {
        this.modifier_id = modifier_id;
        this.name = name;
        this.price = price;
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(int modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Modifiers{" +
                "modifier_id=" + modifier_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", disabled=" + disabled +
                '}';
    }
}
