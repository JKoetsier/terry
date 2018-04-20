package nl.jkoetsier.uva.dbbench.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.workload.expression.Expression;

public abstract class RAJoin extends BinaryRelation {

  protected Expression onExpression;

  public RAJoin(Relation leftInput, Relation rightInput, Expression onExpression) {
    super(leftInput, rightInput);
    this.onExpression = onExpression;
  }

  public RAJoin(Relation leftInput, Relation rightInput) {
    super(leftInput, rightInput);
  }

  public Expression getOnExpression() {
    return onExpression;
  }

  public void setOnExpression(Expression onExpression) {
    this.onExpression = onExpression;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {
    FieldRef left = getLeftInput().getFieldRef(fieldName);

    return left != null ? left : getRightInput().getFieldRef(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    FieldRef left = getLeftInput().getFieldRef(tableName, fieldName);

    return left != null ? left : getRightInput().getFieldRef(tableName, fieldName);
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    List<FieldRef> leftRefs = getLeftInput().getFieldRefsForTable(tableName);
    leftRefs.addAll(getRightInput().getFieldRefsForTable(tableName));

    return leftRefs;
  }
}
