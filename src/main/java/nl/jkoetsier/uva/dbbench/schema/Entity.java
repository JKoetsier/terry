package nl.jkoetsier.uva.dbbench.schema;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

import java.util.LinkedHashMap;

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

    public void addField(Field field) {
        fields.put(field.getName(), field);
    }

    public Field getField(String name) {
        return fields.get(name);
    }
}
