package nl.jkoetsier.terry.intrep.schema.column;

import java.util.List;
import nl.jkoetsier.terry.intrep.schema.visitor.SchemaTreeElement;

public abstract class Column implements SchemaTreeElement {

  private String name;
  private Boolean allowedEmpty = false;
  private Boolean autoGenerated = false;

  public Column() {
  }

  public Column(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  public Boolean isAllowedEmpty() {
    return allowedEmpty;
  }

  public void setAllowedEmpty(Boolean allowedEmpty) {
    this.allowedEmpty = allowedEmpty;
  }

  public Boolean isAutoGenerated() {
    return autoGenerated;
  }

  public void setAutoGenerated(Boolean autoGenerated) {
    this.autoGenerated = autoGenerated;
  }

  public List<String> getArguments() {
    return null;
  }

  public void setArguments(List<String> arguments) {

  }

  public boolean hasArguments() {
    return false;
  }
}
