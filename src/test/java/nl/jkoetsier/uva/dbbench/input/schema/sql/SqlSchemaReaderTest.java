package nl.jkoetsier.uva.dbbench.input.schema.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.BooleanColumn;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.DateColumn;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.DecimalColumn;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Column;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.IntegerColumn;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.VarCharColumn;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Test;

public class SqlSchemaReaderTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Schema getDataModel(String filename) {
    SqlSchemaReader schemaReader = new SqlSchemaReader();
    return schemaReader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  @Test
  public void testCreateTable() {
    Schema dataModel = getDataModel("create_table.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Table table = dataModel.getEntities().get("TableName");
    assertEquals(table.getColumns().size(), 9);
    assertEquals("TableName", table.getName());

    LinkedHashMap<String, Column> fields = table.getColumns();

    assertEquals("Id", fields.get("Id").getName());
    assertEquals("IntField", fields.get("IntField").getName());

    assertTrue(fields.get("Id") instanceof IntegerColumn);
    assertTrue(fields.get("VarChar250Field") instanceof VarCharColumn);
    assertTrue(fields.get("DateField") instanceof DateColumn);
    assertTrue(fields.get("BitField") instanceof BooleanColumn);

    assertNotNull(table.getPrimaryKey());
    assertTrue(table.getPrimaryKey().contains(fields.get("Id")));
  }

  @Test
  public void testCreateTables() {
    Schema dataModel = getDataModel("create_tables.sql");
    assertEquals(dataModel.getEntities().size(), 2);

    Table table1 = dataModel.getEntities().get("TableName");
    Table table2 = dataModel.getEntities().get("Table2Name");
    assertEquals(table1.getColumns().size(), 4);
    assertEquals(table2.getColumns().size(), 5);
    assertEquals("TableName", table1.getName());
    assertEquals("Table2Name", table2.getName());

    LinkedHashMap<String, Column> fields1 = table1.getColumns();
    LinkedHashMap<String, Column> fields2 = table2.getColumns();

    assertEquals("Id", fields1.get("Id").getName());
    assertEquals("IntField", fields1.get("IntField").getName());
    assertEquals("BitField", fields2.get("BitField").getName());

    assertTrue(fields1.get("Id") instanceof IntegerColumn);
    assertTrue(fields1.get("VarChar250Field") instanceof VarCharColumn);
    assertTrue(fields2.get("DateField") instanceof DateColumn);
    assertTrue(fields2.get("BitField") instanceof BooleanColumn);
  }

  @Test
  public void testArguments() {
    Schema dataModel = getDataModel("create_table_arguments.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Table table = dataModel.getEntity("TableName");
    LinkedHashMap<String, Column> fields = table.getColumns();

    assertTrue(5 == ((DecimalColumn) (fields.get("A"))).getPrecision());
    assertTrue(2 == ((DecimalColumn) (fields.get("A"))).getScale());
    assertTrue(200 == ((VarCharColumn) (fields.get("B"))).getLength());
    assertTrue(50 == ((VarCharColumn) (fields.get("C"))).getLength());
  }

  @Test
  public void testNullNotNull() {
    Schema dataModel = getDataModel("create_table_null_not_null.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Table table = dataModel.getEntity("TableName");
    LinkedHashMap<String, Column> fields = table.getColumns();

    assertEquals(false, fields.get("A").isAllowedEmpty());
    assertEquals(true, fields.get("B").isAllowedEmpty());
    assertEquals(true, fields.get("C").isAllowedEmpty());
    assertEquals(false, fields.get("D").isAllowedEmpty());
  }

  @Test
  public void testCompositePrimaryKey() {
    Schema dataModel = getDataModel("create_table_composite_primarykey.sql");
    Table table = dataModel.getEntity("TableName");

    assertTrue(table.getPrimaryKey().contains(table.getColumn("Id")));
    assertTrue(table.getPrimaryKey().contains(table.getColumn("IntField")));
    assertFalse(table.getPrimaryKey().contains(table.getColumn("DecimalColumn")));
  }

}