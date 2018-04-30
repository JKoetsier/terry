package nl.jkoetsier.uva.dbbench.internal.workload.expression;


import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class SelectExpression extends Expression {

  private Expression expression;
  private String alias;

  public SelectExpression(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    expression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    expression.validate(exposedFields);
  }
}
