package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rename extends UnaryRelation {

  private static Logger logger = LoggerFactory.getLogger(Rename.class);

  private String name;
  private ExposedFields exposedFields;

  public Rename(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

//  @Override
//  public ExposedField getFieldRef(String fieldName) {
//    return input.getFieldRef(fieldName);
//  }
//
//  @Override
//  public ExposedField getFieldRef(String tableName, String fieldName) {
//    if (tableName.equals(name)) {
//      return input.getFieldRef(fieldName);
//    }
//
//    return null;
//  }
//
//  @Override
//  public List<ExposedField> getFieldRefsForTable(String tableName) {
//    throw new RuntimeException("Not implemented");
//  }
//
//  @Override
//  public ExposedFields getFieldRefs() {
//    return input.getFieldRefs();
//  }

//  @Override
//  public void validate(Schema schema) throws NotMatchingWorkloadException {
//    input.validate(schema);
//  }

  @Override
  public void acceptVisitor(WorkloadVisitor workloadVisitor) {
    input.acceptVisitor(workloadVisitor);

    workloadVisitor.visit(this);
  }

  @Override
  public ExposedFields getExposedFields() {
    if (exposedFields == null) {
      setExposedFields();
    }

    return exposedFields;
  }

  private void setExposedFields() {
    ExposedFields inputExposedFields = input.getExposedFields();
    ExposedFields thisExposedFields = new ExposedFields();

    for (ExposedField exposedField : inputExposedFields.getAll()) {
      ExposedField cloned = exposedField.clone();
      logger.info("In Rename doing clones, with name: {}", name);
      cloned.setTableAlias(name);
      logger.info(exposedField.getColumnName() + " " + cloned.getColumnName());
      logger.info(exposedField.getTableAlias() + " " + cloned.getTableAlias());
      logger.info(exposedField.getField() + " " + cloned.getField());
      logger.info(exposedField.getTableName() + " " + cloned.getTableName());

      thisExposedFields.add(cloned);
    }

    exposedFields = thisExposedFields;
  }
}
