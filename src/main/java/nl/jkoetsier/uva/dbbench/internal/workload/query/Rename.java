package nl.jkoetsier.uva.dbbench.internal.workload.query;

import nl.jkoetsier.uva.dbbench.internal.workload.visitor.WorkloadVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rename extends UnaryRelation {

  private static Logger logger = LoggerFactory.getLogger(Rename.class);

  private String name;
  private ExposedFields exposedFields;

  public Rename(String name) {
    setName(name);
  }

  private void setName(String name) {
    this.name = name.toLowerCase();
  }

  public String getName() {
    return name;
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
