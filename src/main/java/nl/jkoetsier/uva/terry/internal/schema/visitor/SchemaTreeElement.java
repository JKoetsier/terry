package nl.jkoetsier.uva.terry.internal.schema.visitor;

public interface SchemaTreeElement {

  void acceptVisitor(SchemaVisitor v);
}
