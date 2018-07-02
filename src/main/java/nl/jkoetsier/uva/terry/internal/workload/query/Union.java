package nl.jkoetsier.uva.terry.internal.workload.query;

import nl.jkoetsier.uva.terry.internal.workload.visitor.WorkloadVisitor;

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
  public ExposedFields getExposedFields() {
    return null;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    leftInput.acceptVisitor(workloadVisitor);
    rightInput.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "Union{" +
        "all=" + all +
        ", leftInput=" + leftInput +
        ", rightInput=" + rightInput +
        '}';
  }
}
