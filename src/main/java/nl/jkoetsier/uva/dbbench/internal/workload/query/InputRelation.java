package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class InputRelation extends Relation {

  private Table table;
  private ExposedFields exposedFields;
  private String tableAlias;
  private String tableName;

  public InputRelation(String tableName) {
    setTableName(tableName);
  }

  public InputRelation(String tableName, String tableAlias) {
    setTableAlias(tableAlias);
    setTableName(tableName);
  }

  @Override
  public ExposedFields getExposedFields() {
    return exposedFields;
  }

  @Override
  public HashMap<Table, Integer> getTouchedTables() {
    HashMap<Table, Integer> result = new HashMap<>();
    result.put(table, 1);

    return result;
  }

  public void setExposedFields(
      ExposedFields exposedFields) {
    this.exposedFields = exposedFields;
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
        ", exposedFields=" + exposedFields +
        ", tableAlias='" + tableAlias + '\'' +
        ", tableName='" + tableName + '\'' +
        '}';
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
