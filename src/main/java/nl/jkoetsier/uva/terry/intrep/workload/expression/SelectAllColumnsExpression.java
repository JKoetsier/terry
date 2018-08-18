package nl.jkoetsier.uva.terry.intrep.workload.expression;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectAllColumnsExpression extends Expression {

  private static Logger logger = LoggerFactory.getLogger(SelectAllColumnsExpression.class);

  private String tableName;

  public SelectAllColumnsExpression(String tableName) {
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public String toString() {
    return "SelectAllColumnsExpression{" +
        "tableName='" + tableName + '\'' +
        '}';
  }
}
