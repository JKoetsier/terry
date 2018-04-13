package nl.jkoetsier.uva.dbbench.workload.relalg;

import nl.jkoetsier.uva.dbbench.workload.relalg.expression.Expression;

public class Select extends UnaryRelation {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
