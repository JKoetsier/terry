package nl.jkoetsier.uva.dbbench.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.workload.visitor.WorkloadVisitor;

public class InputRelation extends Relation {

  private Entity entity;
  private FieldRefs fieldRefs;
  private String tableAlias;

  public InputRelation(Entity entity, String tableAlias) {
    this.entity = entity;
    this.tableAlias = tableAlias;
    this.fieldRefs = FieldRefs.create(entity, tableAlias);
  }

  public InputRelation(Entity entity) {
    this.entity = entity;
    this.fieldRefs = FieldRefs.create(entity);
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

  @Override
  public FieldRef getFieldRef(String fieldName) {
    return fieldRefs.get(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    return fieldRefs.get(tableName, fieldName);
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    return fieldRefs.getAllForTable(tableName);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }
}
