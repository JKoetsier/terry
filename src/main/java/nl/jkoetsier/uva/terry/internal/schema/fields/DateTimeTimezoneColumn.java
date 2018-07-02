package nl.jkoetsier.uva.terry.internal.schema.fields;

import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class DateTimeTimezoneColumn extends Column {

  public DateTimeTimezoneColumn() {
  }

  public DateTimeTimezoneColumn(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
