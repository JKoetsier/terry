package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class FunctionExpr extends Expression {

  private String name;
  private ExpressionList expressionList;

  public FunctionExpr(String name,
      ExpressionList expressionList) {
    this.name = name;
    this.expressionList = expressionList;
  }

  public String getName() {
    return name;
  }

  public ExpressionList getExpressionList() {
    return expressionList;
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    expressionList.validate(exposedFields);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    expressionList.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "FunctionExpr{" +
        "name='" + name + '\'' +
        ", expressionList=" + expressionList +
        '}';
  }
}
