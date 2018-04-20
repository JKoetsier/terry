package nl.jkoetsier.uva.dbbench.schema.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class DecimalField extends Field {

  private Integer precision;
  private Integer scale;

  public DecimalField(String name) {
    super(name);
  }

  public DecimalField() {
  }

  public Integer getPrecision() {
    return precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public void setArguments(List<String> arguments) {
    if (arguments.size() > 0) {
      setPrecision(Integer.parseInt(arguments.get(0)));
    }

    if (arguments.size() > 1) {
      setScale(Integer.parseInt(arguments.get(1)));
    }
  }

  @Override
  public List<String> getArguments() {
    if (getPrecision() != null) {
      List<String> arguments =
          new ArrayList<>(Arrays.asList(Integer.toString(getPrecision())));

      if (getScale() != null) {
        arguments.add(Integer.toString(getScale()));
      }

      return arguments;
    }

    return null;
  }

  @Override
  public boolean hasArguments() {
    return getPrecision() != null && getScale() != null;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {
    v.visit(this);
  }
}
