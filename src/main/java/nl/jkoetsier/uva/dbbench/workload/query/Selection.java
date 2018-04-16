package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

public class Selection extends UnaryRelation {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Field getField(String s) {
        return this.getInput().getField(s);
    }
}
