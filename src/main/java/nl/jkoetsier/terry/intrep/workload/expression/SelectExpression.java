package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

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
  public String toString() {
    return "SelectExpression{" +
        "expression=" + expression +
        ", alias='" + alias + '\'' +
        '}';
  }
}
