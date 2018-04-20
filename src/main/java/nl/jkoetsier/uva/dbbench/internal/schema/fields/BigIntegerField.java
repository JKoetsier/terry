package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class BigIntegerField extends Field {

  public BigIntegerField() {
  }

  public BigIntegerField(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
