package nl.jkoetsier.uva.dbbench.schema.fields;

public abstract class Field {

    private String name;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
