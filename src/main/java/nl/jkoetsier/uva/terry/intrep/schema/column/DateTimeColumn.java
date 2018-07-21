package nl.jkoetsier.uva.terry.intrep.schema.column;

import nl.jkoetsier.uva.terry.intrep.schema.visitor.SchemaVisitor;

public class DateTimeColumn extends Column {

  public DateTimeColumn(String name) {
    super(name);
  }

  public DateTimeColumn() {
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
