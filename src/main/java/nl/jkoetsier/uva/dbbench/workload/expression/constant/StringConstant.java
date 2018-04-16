package nl.jkoetsier.uva.dbbench.workload.expression.constant;

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
}
