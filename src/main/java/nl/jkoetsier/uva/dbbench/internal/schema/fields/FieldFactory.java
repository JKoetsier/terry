package nl.jkoetsier.uva.dbbench.internal.schema.fields;

import java.util.HashMap;

public class FieldFactory {

  private static final HashMap<String, Class<? extends Column>> mapping;

  static {
    mapping = new HashMap<>();

    /* Integer fields */
    mapping.put("int", IntegerColumn.class);
    mapping.put("integer", IntegerColumn.class);
    mapping.put("bigint", BigIntegerColumn.class);

    /* Character fields */
    mapping.put("nvarchar", VarCharColumn.class);
    mapping.put("varchar", VarCharColumn.class);
    mapping.put("char", CharColumn.class);

    /* Datetime fields */
    mapping.put("datetime", DateTimeColumn.class);
    mapping.put("datetime2", DateTimeColumn.class);
    mapping.put("datetimeoffset", DateTimeTimezoneColumn.class);
    mapping.put("date", DateColumn.class);

    /* Float fields */
    mapping.put("double", DoubleColumn.class);
    mapping.put("float", FloatColumn.class);

    mapping.put("bit", BooleanColumn.class);

    mapping.put("decimal", DecimalColumn.class);

  }


  public static Column create(String type) {
    Class<? extends Column> fieldClass = mapping.get(type.toLowerCase());

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
