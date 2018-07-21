package nl.jkoetsier.uva.terry.intrep.workload.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import nl.jkoetsier.uva.terry.intrep.schema.column.BooleanColumn;
import org.junit.Test;

public class ExposedColumnTest {

  @Test
  public void testDotConstructor() {
    ExposedField exposedField = new ExposedField("table.column");
    assertEquals("table", exposedField.getTableName());
    assertEquals("column", exposedField.getColumnName());

    exposedField = new ExposedField("column");
    assertEquals("column", exposedField.getColumnName());
    assertNull(exposedField.getTableName());

    exposedField = new ExposedField("table.*");
    assertEquals("*", exposedField.getColumnName());
    assertEquals("table", exposedField.getTableName());
  }

  @Test
  public void testClone() {
    BooleanColumn booleanColumn = new BooleanColumn("boolfield");
    ExposedField exposedField = new ExposedField(booleanColumn, "tableName",
        "columnName", "tableAlias");

    ExposedField cloned = exposedField.clone();
    assertEquals("tableName", exposedField.getTableName());
    assertEquals("columnName", exposedField.getColumnName());
    assertEquals("tableAlias", exposedField.getTableAlias());

    assertSame(exposedField.getColumn(), cloned.getColumn());

    exposedField = new ExposedField("table.column");
    cloned = exposedField.clone();

    assertEquals("table", cloned.getTableName());
    assertEquals("column", cloned.getColumnName());
    assertNull(cloned.getTableAlias());
    assertNull(cloned.getColumn());
  }
}