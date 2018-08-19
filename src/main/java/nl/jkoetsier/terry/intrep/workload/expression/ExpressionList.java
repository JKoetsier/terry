package nl.jkoetsier.terry.intrep.workload.expression;

import java.util.List;
import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

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
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    for (Expression expression : expressions) {
      expression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "ExpressionList{" +
        "expressions=" + expressions +
        '}';
  }
}
