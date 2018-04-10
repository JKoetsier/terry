package nl.jkoetsier.uva.dbbench.datamodel;

import nl.jkoetsier.uva.dbbench.datamodel.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private List<Field> fields;
    private String name;

    public Entity(String name) {
        this.name = name;
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
