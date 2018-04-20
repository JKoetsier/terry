package nl.jkoetsier.uva.dbbench.schema.fields;

import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

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
