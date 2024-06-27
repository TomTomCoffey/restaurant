package learn.models;

import java.math.BigDecimal;

public class Modifiers {
    private int modifier_id;
    private String name;
    private BigDecimal price;

    public Modifiers(){}

    public Modifiers(int modifier_id, String name, BigDecimal price) {
        this.modifier_id = modifier_id;
        this.name = name;
        this.price = price;
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
}
