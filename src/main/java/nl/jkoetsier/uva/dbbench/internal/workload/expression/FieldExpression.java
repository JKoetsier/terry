package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FieldRef;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class FieldExpression extends Expression {

  private FieldRef fieldRef;
  private String fieldName;

  @Override
  public void validate(Schema schema, Relation relation) throws NotMatchingWorkloadException {
    fieldRef = relation.getFieldRef(fieldName);

    if (fieldRef == null) {
      throw new NotMatchingWorkloadException(String.format(
          "Column '%s' does not exist", fieldName
      ));
    }

    isValidated = true;
  }

  public String getFieldName() {
    return fieldName;
  }

  public FieldExpression(String fieldName) {
    this.fieldName = fieldName;
  }

  public FieldRef getFieldRef() {
    return fieldRef;
  }

  public void setFieldRef(FieldRef fieldRef) {
    this.fieldRef = fieldRef;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
