package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;

public class Rename extends UnaryRelation {

    @Override
    public Field getField(String s) {
        throw new RuntimeException("Not implemented");
    }
}
