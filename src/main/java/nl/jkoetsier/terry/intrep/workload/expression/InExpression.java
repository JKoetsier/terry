package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(InExpression.class);
  private Expression leftExpression;
  private Expression rightExpression;

  @Override
  public String toString() {
    return "InExpression{" +
        "leftExpression=" + leftExpression +
        ", rightExpression=" + rightExpression +
        '}';
  }

  public Expression getLeftExpression() {
    return leftExpression;
  }

  public void setLeftExpression(
      Expression leftExpression) {
    this.leftExpression = leftExpression;
  }

  public Expression getRightExpression() {
    return rightExpression;
  }

  public void setRightExpression(
      Expression rightExpression) {
    this.rightExpression = rightExpression;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftExpression.acceptVisitor(workloadVisitor);
    rightExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
