package nl.jkoetsier.uva.terry.intrep.schema.column;

import nl.jkoetsier.uva.terry.intrep.schema.visitor.SchemaVisitor;

public class FloatColumn extends Column {

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
