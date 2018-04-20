package nl.jkoetsier.uva.dbbench.schema.visitor;

public interface SchemaTreeElement {

  void acceptVisitor(SchemaVisitor v);
}
