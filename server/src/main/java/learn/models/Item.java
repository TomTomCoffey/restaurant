package learn.models;

import java.math.BigDecimal;
import java.util.List;

public class Item {

    private int itemId;
    private String title;
    private String description;
    private BigDecimal price;
    private String photo;
    private Category category;
    private List<Modifiers> modifiers;
    private boolean disabled;

    public Item(){}

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", category=" + category +
                ", modifiers=" + modifiers +
                ", disabled=" + disabled +
                "}\n";
    }

    public Item(int itemId, String title, String description,
                BigDecimal price, String photo, Category category,
                List<Modifiers> modifiers, boolean disabled) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
        this.modifiers = modifiers;
        this.disabled = disabled;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Modifiers> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifiers> modifiers) {
        this.modifiers = modifiers;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
