package nl.jkoetsier.uva.dbbench.schema.fields;

import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class BooleanField extends Field {

  public BooleanField() {
  }

  public BooleanField(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
