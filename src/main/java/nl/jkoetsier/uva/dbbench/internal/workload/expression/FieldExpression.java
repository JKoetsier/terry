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
    logger.debug("Validating field {}", fieldName);
    logger.debug("Exposed fields: {}", exposedFields);

    ExposedField existing = exposedFields.get(fieldName);

    logger.debug("Existing: {}", existing);

    if (existing == null) {
      throw new InvalidQueryException(String.format("Not existing field '%s'", fieldName));
    }

    exposedField = existing;

    // Set this fieldName to the string value of the existing exposedField to prevent problems with
    // capitalisation in (for example) Postgres.
    fieldName = existing.toString();
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
  public ExposedFields getExposedFields() {
    return new ExposedFields(getExposedField());
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "FieldExpression{" +
        "exposedField=" + exposedField +
        ", fieldName='" + fieldName + '\'' +
        '}';
  }
}
