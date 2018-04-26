package nl.jkoetsier.uva.dbbench.internal.workload.query;


import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.exception.NotValidatedWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Expression;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Projection extends UnaryRelation {

  private FieldRefs fieldRefs;
  private String tableName;
  private String fieldRefString;
  private Expression limit;
  private Expression offset;

  public Projection(List<FieldRef> fieldRefList) {
    fieldRefs = new FieldRefs(fieldRefList);
  }

  public Projection(String tableName) {
    this.tableName = tableName;
    fieldRefString = String.format("%s.*", tableName);
  }

  public Projection() {
    fieldRefString = "*";
  }

  public FieldRefs getFieldRefs() {
    return fieldRefs;
  }

  public void setFieldRefs(FieldRefs fieldRefs) {
    this.fieldRefs = fieldRefs;
  }

  public Expression getLimit() {
    return limit;
  }

  public void setLimit(Expression limit) {
    this.limit = limit;
  }

  public Expression getOffset() {
    return offset;
  }

  public void setOffset(Expression offset) {
    this.offset = offset;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }
    return fieldRefs.get(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }
    return fieldRefs.get(tableName, fieldName);
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }
    return fieldRefs.getAllForTable(tableName);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    input.validate(schema);

    if (fieldRefs != null) {
      fieldRefs.validate(schema, input);
    } else if (tableName != null) {
      // Project all table columns
      fieldRefs = new FieldRefs();
      fieldRefs.addAll(input.getFieldRefsForTable(tableName));
    } else {
      fieldRefs = input.getFieldRefs();
    }

    isValidated = true;
  }

  public String getFieldRefString() {
    if (fieldRefString == null) {
      return fieldRefs.toString();
    }
    return fieldRefString;
  }
}
