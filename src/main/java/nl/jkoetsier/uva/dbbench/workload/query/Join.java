package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public class Join extends BinaryRelation {
    @Override
    public Field getField(String s) {
        throw new RuntimeException("Not implemented");
    }
}
