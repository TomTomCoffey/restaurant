package learn.models;

public class ModifiersCategory {

    private int id;
    private String name;
    private boolean required;

    public ModifiersCategory(){}

    public ModifiersCategory(int id, String name, boolean required) {
        this.id = id;
        this.name = name;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
