package nl.jkoetsier.uva.dbbench.schema.fields;

import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class DateTimeTimezoneField extends Field {

  public DateTimeTimezoneField() {
  }

  public DateTimeTimezoneField(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
