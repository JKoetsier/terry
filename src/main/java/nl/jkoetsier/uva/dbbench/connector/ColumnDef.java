package nl.jkoetsier.uva.dbbench.connector;

import java.util.List;

public class ColumnDef {

  private String type;
  private String name;
  private Boolean isNull;
  private Boolean isAutoGenerated = false;
  private List<String> arguments;

  public ColumnDef(String type, String name, Boolean isNull) {
    this.type = type;
    this.name = name;
    this.isNull = isNull;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean isNull() {
    return isNull;
  }

  public void setNull(Boolean aNull) {
    isNull = aNull;
  }

  public Boolean isAutoGenerated() {
    return isAutoGenerated;
  }

  public void setAutoGenerated(Boolean autoGenerated) {
    isAutoGenerated = autoGenerated;
  }

  public List<String> getArguments() {
    return arguments;
  }

  public void setArguments(List<String> arguments) {
    this.arguments = arguments;
  }
}