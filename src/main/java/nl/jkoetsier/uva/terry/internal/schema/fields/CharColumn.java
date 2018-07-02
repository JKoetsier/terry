package nl.jkoetsier.uva.terry.internal.schema.fields;

import java.util.Arrays;
import java.util.List;
import nl.jkoetsier.uva.terry.internal.schema.visitor.SchemaVisitor;

public class CharColumn extends Column {

  private Integer length;

  public CharColumn() {
  }

  public CharColumn(String name) {
    super(name);
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  @Override
  public List<String> getArguments() {
    if (getLength() != null) {
      return Arrays.asList(Integer.toString(getLength()));
    }

    return null;
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
  public boolean hasArguments() {
    return getLength() != null;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
