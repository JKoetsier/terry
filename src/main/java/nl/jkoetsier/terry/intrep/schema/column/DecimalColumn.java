package nl.jkoetsier.terry.intrep.schema.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.jkoetsier.terry.intrep.schema.visitor.SchemaVisitor;

public class DecimalColumn extends Column {

  private Integer precision;
  private Integer scale;

  public DecimalColumn(String name) {
    super(name);
  }

  public DecimalColumn() {
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

  public void setArguments(List<String> arguments) {
    if (arguments.size() > 0) {
      setPrecision(Integer.parseInt(arguments.get(0)));
    }

    if (arguments.size() > 1) {
      setScale(Integer.parseInt(arguments.get(1)));
    }
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
