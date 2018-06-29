package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistsExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(ExistsExpression.class);

  private Expression inputExpr;

  public ExistsExpression(Expression inputExpr, boolean not) {
    super(not);
    this.inputExpr = inputExpr;
  }

  public ExistsExpression(Expression inputExpr) {
    this.inputExpr = inputExpr;
  }

  public Expression getInputExpr() {
    return inputExpr;
  }

  public void setInputExpr(Expression inputExpr) {
    this.inputExpr = inputExpr;
  }

  @Override
  public void validate(ExposedFields exposedFields) {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    inputExpr.acceptVisitor(workloadVisitor);
    workloadVisitor.visit(this);
  }
}
