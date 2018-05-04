package nl.jkoetsier.uva.dbbench.internal.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaTreeElement;
import nl.jkoetsier.uva.dbbench.internal.schema.visitor.SchemaVisitor;

public class Entity implements SchemaTreeElement {

  private LinkedHashMap<String, Field> fields;
  private String name;
  private Set<Field> primaryKey;

  public Entity(String name) {
    this.name = name;
    fields = new LinkedHashMap<>();
  }

  public LinkedHashMap<String, Field> getFields() {
    return fields;
  }

  public void setFields(LinkedHashMap<String, Field> fields) {
    this.fields = fields;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addField(Field field) {
    fields.put(field.getName(), field);
  }

  public Field getField(String name) {
    return fields.get(name);
  }

  public Set<Field> getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(Set<Field> primaryKey) {
    this.primaryKey = primaryKey;
  }

  public List<String> getPrimaryKeyFieldNames() {
    List<String> keyFields = new ArrayList<>();

    if (primaryKey == null) {
      return keyFields;
    }

    for (Field field : primaryKey) {
      keyFields.add(field.getName());
    }

    Collections.reverse(keyFields);

    return keyFields;
  }

  @Override
  public void acceptVisitor(SchemaVisitor v) {

    for (Entry<String, Field> fieldEntry : fields.entrySet()) {
      fieldEntry.getValue().acceptVisitor(v);
    }

    v.visit(this);
  }
}
