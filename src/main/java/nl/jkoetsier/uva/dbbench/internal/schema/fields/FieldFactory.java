package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import java.util.HashMap;

public class FieldFactory {

  private static final HashMap<String, Class<? extends Field>> mapping;

  static {
    mapping = new HashMap<>();

    /* Integer fields */
    mapping.put("int", IntegerField.class);
    mapping.put("bigint", BigIntegerField.class);

    /* Character fields */
    mapping.put("nvarchar", VarCharField.class);
    mapping.put("varchar", VarCharField.class);
    mapping.put("char", CharField.class);

    /* Datetime fields */
    mapping.put("datetime", DateTimeField.class);
    mapping.put("datetime2", DateTimeField.class);
    mapping.put("datetimeoffset", DateTimeTimezoneField.class);
    mapping.put("date", DateField.class);

    /* Float fields */
    mapping.put("double", DoubleField.class);
    mapping.put("float", FloatField.class);

    mapping.put("bit", BooleanField.class);

    mapping.put("decimal", DecimalField.class);

  }


  public static Field create(String type) {
    Class<? extends Field> fieldClass = mapping.get(type.toLowerCase());

    if (fieldClass == null) {
      throw new RuntimeException(String.format("Can't find class for type [%s]", type));
    }

    try {
      return fieldClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
