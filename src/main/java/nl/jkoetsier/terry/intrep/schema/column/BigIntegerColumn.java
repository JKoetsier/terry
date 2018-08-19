package nl.jkoetsier.terry.intrep.schema.column;

import nl.jkoetsier.terry.intrep.schema.visitor.SchemaVisitor;

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
