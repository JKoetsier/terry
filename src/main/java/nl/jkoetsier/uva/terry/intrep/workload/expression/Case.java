package nl.jkoetsier.uva.terry.intrep.workload.expression;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Case extends Expression {

  private static Logger logger = LoggerFactory.getLogger(Case.class);

  private Expression condition;
  private Expression trueExpression;
  private Expression falseExpression;

  public Expression getCondition() {
    return condition;
  }

  public void setCondition(Expression condition) {
    this.condition = condition;
  }

  public Expression getTrueExpression() {
    return trueExpression;
  }

  public void setTrueExpression(
      Expression trueExpression) {
    this.trueExpression = trueExpression;
  }

  public Expression getFalseExpression() {
    return falseExpression;
  }

  public void setFalseExpression(
      Expression falseExpression) {
    this.falseExpression = falseExpression;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    condition.acceptVisitor(workloadVisitor);
    trueExpression.acceptVisitor(workloadVisitor);
    falseExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "Case{" +
        "condition=" + condition +
        ", trueExpression=" + trueExpression +
        ", falseExpression=" + falseExpression +
        '}';
  }
}
