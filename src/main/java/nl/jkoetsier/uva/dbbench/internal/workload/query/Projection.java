package nl.jkoetsier.uva.dbbench.internal.workload.query;


import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.exception.NotValidatedWorkloadException;
import nl.jkoetsier.uva.dbbench.input.util.IdentifierValidator;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Projection extends UnaryRelation {

  private FieldRefs fieldRefs = new FieldRefs();
  private List<String> selectColumns;
  private String tableName;

  public Projection(List<String> selectColumns) {
    this.selectColumns = selectColumns;
  }

  public Projection(String tableName) {
    this.tableName = tableName;
  }
  public Projection() {

  }

  public FieldRefs getFieldRefs() {
    return fieldRefs;
  }

  public void setFieldRefs(FieldRefs fieldRefs) {
    this.fieldRefs = fieldRefs;
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
    workloadVisitor.visit(this);
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    input.validate(schema);

    if (tableName != null) {
      // Project all table columns
      fieldRefs.addAll(input.getFieldRefsForTable(tableName));
    } else if (selectColumns != null) {
      // Project selection of columns
      for (String selectColumn : selectColumns) {

        if (IdentifierValidator.isValidTableIdentifier(selectColumn)) {
          FieldRef fieldRef = input.getFieldRef(selectColumn);

          if (fieldRef == null) {
            throw new NotMatchingWorkloadException(String.format(
                "Field '%s' does not exist", selectColumn
            ));
          }

          fieldRefs.add(fieldRef);
        }
      }
    } else {
      throw new RuntimeException("Jaap, you may want to have a look here");
    }

    isValidated = true;
  }
}
