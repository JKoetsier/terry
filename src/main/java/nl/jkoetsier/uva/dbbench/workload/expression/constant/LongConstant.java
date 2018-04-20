package nl.jkoetsier.uva.dbbench.workload.expression.constant;

import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class LongConstant extends Constant {

  private Long value;

  public LongConstant(Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
