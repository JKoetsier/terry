package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class IntegerField extends Field {

  public IntegerField() {
  }

  public IntegerField(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
