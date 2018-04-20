package nl.jkoetsier.uva.dbbench.internal.schema.visitor;

public interface SchemaTreeElement {

  void acceptVisitor(SchemaVisitor v);
}
