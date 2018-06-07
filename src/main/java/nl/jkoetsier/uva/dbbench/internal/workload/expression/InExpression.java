package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InExpression extends Expression {

  @Override
  public String toString() {
    return "InExpression{" +
        "leftExpression=" + leftExpression +
        ", rightExpression=" + rightExpression +
        '}';
  }

  private static Logger logger = LoggerFactory.getLogger(InExpression.class);

  private Expression leftExpression;
  private Expression rightExpression;

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
  public void validate(ExposedFields exposedFields) {
    leftExpression.validate(exposedFields);
    rightExpression.validate(exposedFields);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftExpression.acceptVisitor(workloadVisitor);
    rightExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
