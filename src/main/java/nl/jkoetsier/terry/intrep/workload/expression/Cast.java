package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class Cast extends Expression {

  private Expression expression;
  private String type;

  public Cast(Expression expression, String type) {
    this.expression = expression;
    this.type = type;
  }

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    expression.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "Cast{" +
        "expression=" + expression +
        ", type='" + type + '\'' +
        '}';
  }
}
