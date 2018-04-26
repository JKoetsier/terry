package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Rename extends UnaryRelation {

  private String name;

  public Rename(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {
    return input.getFieldRef(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    if (tableName.equals(name)) {
      return input.getFieldRef(fieldName);
    }

    return null;
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public FieldRefs getFieldRefs() {
    return input.getFieldRefs();
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    input.validate(schema);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }
}
