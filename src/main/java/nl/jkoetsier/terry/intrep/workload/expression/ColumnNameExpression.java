package nl.jkoetsier.terry.intrep.workload.expression;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnNameExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(ColumnNameExpression.class);
  private String fullName;

  public ColumnNameExpression(String fullName) {
    setFullName(fullName);
  }

  public String getFullName() {
    return fullName;
  }

  public String getColumnName() {
    String[] splitted = fullName.split("\\.");

    return splitted[splitted.length - 1];
  }

  private void setFullName(String fullName) {
    this.fullName = fullName.toLowerCase();
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "ColumnNameExpression{" +
        "fullName='" + fullName + '\'' +
        '}';
  }
}
