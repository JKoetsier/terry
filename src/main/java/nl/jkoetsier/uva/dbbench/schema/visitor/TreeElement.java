package nl.jkoetsier.uva.dbbench.schema.visitor;

public interface TreeElement {

  void acceptVisitor(SchemaVisitor v);
}
