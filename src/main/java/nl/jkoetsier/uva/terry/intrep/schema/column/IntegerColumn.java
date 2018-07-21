package nl.jkoetsier.uva.terry.intrep.schema.column;

import nl.jkoetsier.uva.terry.intrep.schema.visitor.SchemaVisitor;

public class IntegerColumn extends Column {

  public IntegerColumn() {
  }

  public IntegerColumn(String name) {
    super(name);
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
