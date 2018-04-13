package nl.jkoetsier.uva.dbbench.workload.relalg;

import nl.jkoetsier.uva.dbbench.datamodel.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Project extends UnaryRelation {

    private List<Field> fields;

    public Project(List<Field> fields) {
        this.fields = fields;
    }

    public Project() {
        this(new ArrayList<>());
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
