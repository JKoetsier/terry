package nl.jkoetsier.uva.terry.intrep.schema.column;

import nl.jkoetsier.uva.terry.intrep.schema.visitor.SchemaVisitor;

public class DoubleColumn extends Column {

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
