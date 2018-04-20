package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class DateField extends Field {

  public DateField(String name) {
    super(name);
  }

  public DateField() {
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
