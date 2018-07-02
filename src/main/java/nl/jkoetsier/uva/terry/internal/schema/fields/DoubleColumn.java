package nl.jkoetsier.uva.terry.internal.schema.fields;

import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class DoubleColumn extends Column {

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
