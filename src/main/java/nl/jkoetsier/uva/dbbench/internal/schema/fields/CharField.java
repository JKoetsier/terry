package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class CharField extends Field {

  public CharField() {
  }

  public CharField(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
