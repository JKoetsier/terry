package nl.jkoetsier.uva.dbbench.datamodel;

import nl.jkoetsier.uva.dbbench.datamodel.fields.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Entity {

    private LinkedHashMap<String, Field> fields;
    private String name;

    public Entity(String name) {
        this.name = name;
        fields = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Field> getFields() {
        return fields;
    }

    public void setFields(LinkedHashMap<String, Field> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
