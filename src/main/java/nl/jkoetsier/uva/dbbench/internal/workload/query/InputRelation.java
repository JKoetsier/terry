package nl.jkoetsier.uva.dbbench.internal.workload.query;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.exception.NotValidatedWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;

public class InputRelation extends Relation {

  private Entity entity;
  private FieldRefs fieldRefs;
  private String tableAlias;
  private String tableName;

  public InputRelation(String tableName) {
    this.tableName = tableName;
  }

  public InputRelation(String tableName, String tableAlias) {
    this.tableName = tableName;
    this.tableAlias = tableAlias;
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
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }

    return fieldRefs.get(fieldName);
  }

  @Override
  public FieldRef getFieldRef(String tableName, String fieldName) {
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }

    return fieldRefs.get(tableName, fieldName);
  }

  @Override
  public List<FieldRef> getFieldRefsForTable(String tableName) {
    if (!isValidated) {
      throw new NotValidatedWorkloadException();
    }

    return fieldRefs.getAllForTable(tableName);
  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    workloadVisitor.visit(this);
  }

  @Override
  public void validate(Schema schema) throws NotMatchingWorkloadException {
    Entity entity = schema.getEntity(this.tableName);

    if (entity == null) {
      throw new NotMatchingWorkloadException(String.format(
          "Entity '%s' does not exist", this.tableName
      ));
    }

    this.entity = entity;

    if (this.tableAlias != null) {
      this.fieldRefs = FieldRefs.create(entity, this.tableAlias);
    } else {
      this.fieldRefs = FieldRefs.create(entity);
    }

    this.isValidated = true;
  }
}
