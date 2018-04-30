package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class InputRelation extends Relation {

  private Entity entity;
  private ExposedFields exposedFields;
  private String tableAlias;
  private String tableName;

  public InputRelation(String tableName) {
    this.tableName = tableName;
  }

  public InputRelation(String tableName, String tableAlias) {
    this.tableName = tableName;
    this.tableAlias = tableAlias;
  }

  @Override
  public ExposedFields getExposedFields() {
    return exposedFields;
  }

  public void setExposedFields(
      ExposedFields exposedFields) {
    this.exposedFields = exposedFields;
  }

  public Entity getEntity() {
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  public String getTableName() {
    return tableName;
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
