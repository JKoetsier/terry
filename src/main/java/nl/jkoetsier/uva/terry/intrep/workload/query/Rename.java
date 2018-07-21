package nl.jkoetsier.uva.terry.intrep.workload.query;

import nl.jkoetsier.uva.terry.intrep.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rename extends UnaryRelation {

  private static Logger logger = LoggerFactory.getLogger(Rename.class);

  private String name;
  private ExposedFields exposedFields;

  public Rename(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name.toLowerCase();
  }

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
      cloned.setTableAlias(name);

      thisExposedFields.add(cloned);
    }

    exposedFields = thisExposedFields;
  }

  @Override
  public String toString() {
    return "Rename{" +
        "name='" + name + '\'' +
        ", exposedFields=" + exposedFields +
        ", input=" + input +
        '}';
  }
}