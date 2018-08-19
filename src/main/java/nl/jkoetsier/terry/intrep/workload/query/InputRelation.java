package nl.jkoetsier.terry.intrep.workload.query;

import nl.jkoetsier.terry.intrep.workload.visitor.WorkloadVisitor;
import nl.jkoetsier.terry.intrep.schema.Table;

public class InputRelation extends Relation {

  private Table table;
  private String tableAlias;
  private String tableName;

  public InputRelation(String tableName) {
    setTableName(tableName);
  }

  public InputRelation(String tableName, String tableAlias) {
    setTableAlias(tableAlias);
    setTableName(tableName);
  }

  public Table getTable() {
    return table;
  }

  public void setTable(Table table) {
    this.table = table;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  private void setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias.toLowerCase();
  }

  public String getTableName() {
    return tableName;
  }

  private void setTableName(String tableName) {
    this.tableName = tableName.toLowerCase();
  }

  @Override
  public String toString() {
    return "InputRelation{" +
        "table=" + table +
        ", tableAlias='" + tableAlias + '\'' +
        ", tableName='" + tableName + '\'' +
        '}';
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
