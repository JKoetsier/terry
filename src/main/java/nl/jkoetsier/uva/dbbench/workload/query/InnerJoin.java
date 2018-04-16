package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

public class InnerJoin extends RAJoin {
    public InnerJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
        super(leftInput, rightInput, onExpression);
    }

    public InnerJoin(Relation leftInput, Relation rightInput) {
        super(leftInput, rightInput);
    }
}
