package nl.jkoetsier.uva.dbbench.internal.workload.expression;

import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedField;
import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(FieldExpression.class);
  private ExposedField exposedField;
  private String fieldName;

  public FieldExpression(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public void validate(ExposedFields exposedFields) {
    ExposedField existing = exposedFields.get(fieldName);
    System.out.println(exposedFields);

    if (existing == null) {
      throw new InvalidQueryException(String.format("Not existing field '%s'", fieldName));
    }

    exposedField = existing;
  }

  public String getFieldName() {
    return fieldName;
  }

  public ExposedField getExposedField() {
    return exposedField;
  }

  public void setExposedField(ExposedField exposedField) {
    this.exposedField = exposedField;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
