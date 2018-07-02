package nl.jkoetsier.uva.terry.internal.schema.fields;

import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class BooleanColumn extends Column {

  public BooleanColumn() {
  }

  public BooleanColumn(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
