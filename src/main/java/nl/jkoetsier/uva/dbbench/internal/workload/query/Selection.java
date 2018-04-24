package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.exception.NotValidatedWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Selection extends UnaryRelation {

  private Expression whereExpression;

  public Expression getWhereExpression() {
    return whereExpression;
  }

  public void setWhereExpression(Expression whereExpression) {
    this.whereExpression = whereExpression;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {
    return input.getFieldRef(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    return input.getFieldRef(tableName, fieldName);
  }

  @Override
  public FieldRefs getFieldRefs() {
    return input.getFieldRefs();
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    return input.getFieldRefsForTable(tableName);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    if (whereExpression != null) {
      whereExpression.acceptVisitor(workloadVisitor);
    }

    workloadVisitor.visit(this);
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    input.validate(schema);

    if (whereExpression != null) {
      whereExpression.validate(schema, this);
    }

    isValidated = true;
  }
}
