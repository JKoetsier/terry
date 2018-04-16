package nl.jkoetsier.uva.dbbench.workload.query;


import java.util.List;

public class Projection extends UnaryRelation {

    private FieldRefs fieldRefs;

    public Projection(FieldRefs fieldRefs) {
        this.fieldRefs = fieldRefs;
    }

    public Projection() {

    }

    public FieldRefs getFieldRefs() {
        return fieldRefs;
    }

    public void setFieldRefs(FieldRefs fieldRefs) {
        this.fieldRefs = fieldRefs;
    }

    @Override
    public FieldRef getFieldRef(String fieldName) {
        return fieldRefs.get(fieldName);
    }

    @Override
    public FieldRef getFieldRef(String tableName, String fieldName) {
        return fieldRefs.get(tableName, fieldName);
    }

    @Override
    public List<FieldRef> getFieldRefsForTable(String tableName) {
        return fieldRefs.getAllForTable(tableName);
    }
}
