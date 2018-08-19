package nl.jkoetsier.terry.intrep.schema.column;

import nl.jkoetsier.terry.intrep.schema.visitor.SchemaVisitor;

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
