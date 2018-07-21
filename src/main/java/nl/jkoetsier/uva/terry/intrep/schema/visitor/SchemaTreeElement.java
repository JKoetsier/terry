package nl.jkoetsier.uva.terry.intrep.schema.visitor;

public interface SchemaTreeElement {

  void acceptVisitor(SchemaVisitor v);
}
