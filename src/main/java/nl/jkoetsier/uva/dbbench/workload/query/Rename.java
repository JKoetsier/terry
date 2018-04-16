package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

import java.util.List;

public class Rename extends UnaryRelation {

    @Override
    public FieldRef getFieldRef(String fieldName) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public FieldRef getFieldRef(String tableName, String fieldName) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<FieldRef> getFieldRefsForTable(String tableName) {
        throw new RuntimeException("Not implemented");
    }
}
