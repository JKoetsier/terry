package nl.jkoetsier.uva.dbbench.schema.fields;

import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class DoubleField extends Field {

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
