package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
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
  public void validate(ExposedFields exposedFields) {
    condition.validate(exposedFields);
    trueExpression.validate(exposedFields);
    falseExpression.validate(exposedFields);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    condition.acceptVisitor(workloadVisitor);
    trueExpression.acceptVisitor(workloadVisitor);
    falseExpression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
