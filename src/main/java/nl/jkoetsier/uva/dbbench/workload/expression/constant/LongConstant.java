package nl.jkoetsier.uva.dbbench.workload.expression.constant;

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
}
