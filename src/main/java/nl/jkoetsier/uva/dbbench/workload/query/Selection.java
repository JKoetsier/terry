package nl.jkoetsier.uva.dbbench.workload.query;

import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

import java.util.List;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class Selection extends UnaryRelation {

  private Expression expression;

  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {
    return getInput().getFieldRef(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    return getInput().getFieldRef(tableName, fieldName);
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    return getInput().getFieldRefsForTable(tableName);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
