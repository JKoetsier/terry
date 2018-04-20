package nl.jkoetsier.uva.dbbench.schema.fields;

import java.util.Arrays;
import java.util.List;
import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class VarCharField extends Field {

  private Integer length;

  public VarCharField() {
  }

  public VarCharField(String name) {
    super(name);
  }

  public VarCharField(String name, Integer length) {
    super(name);
    this.length = length;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  @Override
  public void setArguments(List<String> arguments) {
    if (arguments.size() > 0) {
      try {
        setLength(Integer.parseInt(arguments.get(0)));
      } catch (NumberFormatException e) {
        // Ignore keywords such as "max". Only accept integers
      }
    }
  }

  @Override
  public List<String> getArguments() {
    if (getLength() != null) {
      return Arrays.asList(Integer.toString(getLength()));
    }

    return null;
  }

  @Override
  public boolean hasArguments() {
    return getLength() != null;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
