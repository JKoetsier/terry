package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Projection extends UnaryRelation {

    private List<Field> fields;

    public Projection(List<Field> fields) {
        this.fields = fields;
    }

    public Projection() {
        this(new ArrayList<>());
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public Field getField(String s) {
        for (Field field : fields) {
            if (field.getName().equals(s)) {
                return field;
            }
        }

        return null;
    }
}
