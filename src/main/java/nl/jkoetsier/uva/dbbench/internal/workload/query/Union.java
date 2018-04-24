package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class Union extends BinaryRelation {

  private boolean all;

  public Union(Relation leftInput,
      Relation rightInput, boolean all) {
    super(leftInput, rightInput);
    this.all = all;
  }

  public boolean isAll() {
    return all;
  }

  @Override
  public FieldRef getFieldRef(String fieldName) {

    return null;
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    return null;
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    return null;
  }

  @Override
  public FieldRefs getFieldRefs() {
    return null;
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    leftInput.validate(schema);
    rightInput.validate(schema);

    isValidated = true;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftInput.acceptVisitor(workloadVisitor);
    rightInput.acceptVisitor(workloadVisitor);
  }
}
