package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldRefs {

    private List<FieldRef> fieldRefs;

    public FieldRefs() {
        fieldRefs = new ArrayList<>();
    }

    public FieldRefs(List<FieldRef> fieldRefs) {
        this.fieldRefs = fieldRefs;
    }

    public void add(FieldRef fieldRef) {
        fieldRefs.add(fieldRef);
    }

    public void addAll(List<FieldRef> fieldRefList) {
        fieldRefs.addAll(fieldRefList);
    }

    public FieldRef get(String name) {
        String[] splitOnDot = name.split("\\.");

        if (splitOnDot.length > 1) {
            return get(splitOnDot[0], splitOnDot[1]);
        }

        for (FieldRef fieldRef : fieldRefs) {
            if (fieldRef.getColumnName().equals(name)) {
                return fieldRef;
            }
        }

        return null;
    }


    public FieldRef get(String tableName, String fieldName) {
        for (FieldRef fieldRef : fieldRefs) {
            if (fieldRef.getColumnName().equals(fieldName) &&
                    (fieldRef.getTableName().equals(tableName) ||
                            (fieldRef.getTableAlias() != null &&
                    fieldRef.getTableAlias().equals(tableName)))) {
                return fieldRef;
            }
        }

        return null;
    }

    public List<FieldRef> getAllForTable(String tableName) {
        List<FieldRef> resultRefs = new ArrayList<>();

        for (FieldRef fieldRef : fieldRefs) {
            if (fieldRef.getTableName().equals(tableName)) {
                resultRefs.add(fieldRef);
            }
        }

        return resultRefs;
    }

    public static FieldRefs create(Entity entity) {
        FieldRefs fieldRefs = new FieldRefs();

        for (Field field : entity.getFields().values()) {
            FieldRef fieldRef = new FieldRef(field, entity.getName(), field.getName());
            fieldRefs.add(fieldRef);
        }

        return fieldRefs;
    }

    public static FieldRefs create(Entity entity, String tableAlias) {
        FieldRefs fieldRefs = new FieldRefs();

        for (Field field : entity.getFields().values()) {
            FieldRef fieldRef = new FieldRef(field, entity.getName(), field.getName(), tableAlias);
            fieldRefs.add(fieldRef);
        }

        return fieldRefs;
    }
}
