package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class FunctionExpr extends Expression {

  private String name;
  private ExpressionList expressionList;
  private boolean distinct = false;

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

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }
}
