package nl.jkoetsier.uva.terry.internal.schema.fields;

import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class DateColumn extends Column {

  public DateColumn(String name) {
    super(name);
  }

  public DateColumn() {
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
