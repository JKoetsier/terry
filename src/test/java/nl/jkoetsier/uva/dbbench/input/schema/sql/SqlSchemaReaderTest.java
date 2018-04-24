package nl.jkoetsier.uva.dbbench.input.schema.sql;

import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SqlSchemaReaderTest {

  String dataDirectory = "/data/sql/";

  private String getFilepath(String filename) {
    return getClass().getResource(dataDirectory + filename).getFile();
  }

  private Schema getDataModel(String filename) {
    SqlSchemaReader schemaReader = new SqlSchemaReader();
    return schemaReader.fromFile(getFilepath(filename));
  }

  @Before
  public void resetDataModel() {
    Schema.getInstance().setEntities(new HashMap<>());
  }

  @Test
  public void testCreateTable() {
    Schema dataModel = getDataModel("create_table.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Entity entity = dataModel.getEntities().get("TableName");
    assertEquals(entity.getFields().size(), 9);
    assertEquals("TableName", entity.getName());

    LinkedHashMap<String, Field> fields = entity.getFields();

    assertEquals("Id", fields.get("Id").getName());
    assertEquals("IntField", fields.get("IntField").getName());

    assertTrue(fields.get("Id") instanceof IntegerField);
    assertTrue(fields.get("VarChar250Field") instanceof VarCharField);
    assertTrue(fields.get("DateField") instanceof DateField);
    assertTrue(fields.get("BitField") instanceof BooleanField);

    assertNotNull(entity.getPrimaryKey());
    assertTrue(entity.getPrimaryKey().contains(fields.get("Id")));
  }

  @Test
  public void testCreateTables() {
    Schema dataModel = getDataModel("create_tables.sql");
    assertEquals(dataModel.getEntities().size(), 2);

    Entity entity1 = dataModel.getEntities().get("TableName");
    Entity entity2 = dataModel.getEntities().get("Table2Name");
    assertEquals(entity1.getFields().size(), 4);
    assertEquals(entity2.getFields().size(), 5);
    assertEquals("TableName", entity1.getName());
    assertEquals("Table2Name", entity2.getName());

    LinkedHashMap<String, Field> fields1 = entity1.getFields();
    LinkedHashMap<String, Field> fields2 = entity2.getFields();

    assertEquals("Id", fields1.get("Id").getName());
    assertEquals("IntField", fields1.get("IntField").getName());
    assertEquals("BitField", fields2.get("BitField").getName());

    assertTrue(fields1.get("Id") instanceof IntegerField);
    assertTrue(fields1.get("VarChar250Field") instanceof VarCharField);
    assertTrue(fields2.get("DateField") instanceof DateField);
    assertTrue(fields2.get("BitField") instanceof BooleanField);
  }

  @Test
  public void testArguments() {
    Schema dataModel = getDataModel("create_table_arguments.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Entity entity = dataModel.getEntity("TableName");
    LinkedHashMap<String, Field> fields = entity.getFields();

    assertTrue(5 == ((DecimalField)(fields.get("A"))).getPrecision());
    assertTrue(2 == ((DecimalField)(fields.get("A"))).getScale());
    assertTrue(200 == ((VarCharField)(fields.get("B"))).getLength());
    assertTrue(50 == ((VarCharField)(fields.get("C"))).getLength());
  }

  @Test
  public void testNullNotNull() {
    Schema dataModel = getDataModel("create_table_null_not_null.sql");
    assertEquals(dataModel.getEntities().size(), 1);

    Entity entity = dataModel.getEntity("TableName");
    LinkedHashMap<String, Field> fields = entity.getFields();

    assertEquals(false, fields.get("A").isAllowedEmpty());
    assertEquals(true, fields.get("B").isAllowedEmpty());
    assertEquals(true, fields.get("C").isAllowedEmpty());
    assertEquals(false, fields.get("D").isAllowedEmpty());
  }

  @Test
  public void testCompositePrimaryKey() {
    Schema dataModel = getDataModel("create_table_composite_primarykey.sql");
    Entity entity = dataModel.getEntity("TableName");

    assertTrue(entity.getPrimaryKey().contains(entity.getField("Id")));
    assertTrue(entity.getPrimaryKey().contains(entity.getField("IntField")));
    assertFalse(entity.getPrimaryKey().contains(entity.getField("DecimalField")));
  }
}