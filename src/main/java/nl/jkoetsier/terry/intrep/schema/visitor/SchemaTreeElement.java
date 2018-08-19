package nl.jkoetsier.terry.intrep.schema.visitor;

public interface SchemaTreeElement {

  void acceptVisitor(SchemaVisitor v);
}
