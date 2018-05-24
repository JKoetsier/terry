package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(InExpression.class);

  private Expression leftExpression;
  private Expression rightExpression;
  private boolean isNot;

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

  public boolean isNot() {
    return isNot;
  }

  public void setNot(boolean not) {
    isNot = not;
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
