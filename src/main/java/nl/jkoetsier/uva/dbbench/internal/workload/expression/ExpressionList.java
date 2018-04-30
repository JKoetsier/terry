package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class ExpressionList extends Expression {

  private List<Expression> expressions;

  public ExpressionList(
      List<Expression> expressions) {
    this.expressions = expressions;
  }

  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    for (Expression expression : expressions) {
      expression.validate(exposedFields);
    }
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Expression expression : expressions) {
      expression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }
}
