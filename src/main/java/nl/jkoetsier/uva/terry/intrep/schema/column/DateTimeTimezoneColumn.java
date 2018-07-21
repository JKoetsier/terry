package nl.jkoetsier.uva.terry.intrep.schema.column;

import nl.jkoetsier.uva.terry.intrep.schema.visitor.SchemaVisitor;

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
