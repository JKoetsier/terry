package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
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
  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {

  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    expressionList.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
