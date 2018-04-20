package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class DoubleField extends Field {

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
