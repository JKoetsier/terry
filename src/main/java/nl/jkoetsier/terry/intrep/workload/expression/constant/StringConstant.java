package nl.jkoetsier.terry.intrep.workload.expression.constant;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;

public class StringConstant extends Constant {

  private String value;

  public StringConstant(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "StringConstant{" +
        "value='" + value + '\'' +
        '}';
  }
}
