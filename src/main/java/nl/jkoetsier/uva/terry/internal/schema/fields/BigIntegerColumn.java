package nl.jkoetsier.uva.terry.internal.schema.fields;

import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class BigIntegerColumn extends Column {

  public BigIntegerColumn() {
  }

  public BigIntegerColumn(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
