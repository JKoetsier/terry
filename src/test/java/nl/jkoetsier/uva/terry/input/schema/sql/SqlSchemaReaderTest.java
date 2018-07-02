package nl.jkoetsier.uva.terry.input.schema.sql;

import static nl.jkoetsier.uva.terry.util.Assertions.assertIdentifierEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.schema.Table;
import nl.jkoetsier.uva.terry.internal.schema.fields.BooleanColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DateColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DecimalColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.IntegerColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.VarCharColumn;
import nl.jkoetsier.uva.terry.util.TestDataHelper;
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
    assertEquals(dataModel.getTables().size(), 1);

    Table table = dataModel.getTable("TableName");
    assertEquals(table.getColumns().size(), 9);
    assertIdentifierEquals("TableName", table.getName());

    assertIdentifierEquals("Id", table.getColumn("Id").getName());
    assertIdentifierEquals("IntField", table.getColumn("IntField").getName());

    assertTrue(table.getColumn("Id") instanceof IntegerColumn);
    assertTrue(table.getColumn("VarChar250Field") instanceof VarCharColumn);
    assertTrue(table.getColumn("DateField") instanceof DateColumn);
    assertTrue(table.getColumn("BitField") instanceof BooleanColumn);

    assertNotNull(table.getPrimaryKey());
    assertTrue(table.getPrimaryKey().contains(table.getColumn("Id")));
  }

  @Test
  public void testCreateTables() {
    Schema dataModel = getDataModel("create_tables.sql");
    assertEquals(dataModel.getTables().size(), 2);

    Table table1 = dataModel.getTable("TableName");
    Table table2 = dataModel.getTable("Table2Name");
    assertEquals(table1.getColumns().size(), 4);
    assertEquals(table2.getColumns().size(), 5);
    assertIdentifierEquals("TableName", table1.getName());
    assertIdentifierEquals("Table2Name", table2.getName());

    assertIdentifierEquals("Id", table1.getColumn("Id").getName());
    assertIdentifierEquals("IntField", table1.getColumn("IntField").getName());
    assertIdentifierEquals("BitField", table2.getColumn("BitField").getName());

    assertTrue(table1.getColumn("Id") instanceof IntegerColumn);
    assertTrue(table1.getColumn("VarChar250Field") instanceof VarCharColumn);
    assertTrue(table2.getColumn("DateField") instanceof DateColumn);
    assertTrue(table2.getColumn("BitField") instanceof BooleanColumn);
  }

  @Test
  public void testArguments() {
    Schema dataModel = getDataModel("create_table_arguments.sql");
    assertEquals(dataModel.getTables().size(), 1);

    Table table = dataModel.getTable("TableName");

    assertTrue(5 == ((DecimalColumn) (table.getColumn("A"))).getPrecision());
    assertTrue(2 == ((DecimalColumn) (table.getColumn("A"))).getScale());
    assertTrue(200 == ((VarCharColumn) (table.getColumn("B"))).getLength());
    assertTrue(50 == ((VarCharColumn) (table.getColumn("C"))).getLength());
  }

  @Test
  public void testNullNotNull() {
    Schema dataModel = getDataModel("create_table_null_not_null.sql");
    assertEquals(dataModel.getTables().size(), 1);

    Table table = dataModel.getTable("TableName");

    assertEquals(false, table.getColumn("A").isAllowedEmpty());
    assertEquals(true, table.getColumn("B").isAllowedEmpty());
    assertEquals(true, table.getColumn("C").isAllowedEmpty());
    assertEquals(false, table.getColumn("D").isAllowedEmpty());
  }

  @Test
  public void testCompositePrimaryKey() {
    Schema dataModel = getDataModel("create_table_composite_primarykey.sql");
    Table table = dataModel.getTable("TableName");

    assertTrue(table.getPrimaryKey().contains(table.getColumn("Id")));
    assertTrue(table.getPrimaryKey().contains(table.getColumn("IntField")));
    assertFalse(table.getPrimaryKey().contains(table.getColumn("DecimalColumn")));
  }

}