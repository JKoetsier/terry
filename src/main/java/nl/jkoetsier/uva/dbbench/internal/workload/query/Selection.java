package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Selection extends UnaryRelation {

  private Expression whereExpression;

  public Expression getWhereExpression() {
    return whereExpression;
  }

  public void setWhereExpression(Expression whereExpression) {
    this.whereExpression = whereExpression;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    if (whereExpression != null) {
      whereExpression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public ExposedFields getExposedFields() {
    return input.getExposedFields();
  }

  @Override
  public String toString() {
    return "Selection{" +
        "whereExpression=" + whereExpression +
        ", input=" + input +
        '}';
  }
}
