package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.datamodel.Entity;
import nl.jkoetsier.uva.dbbench.datamodel.fields.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TSqlSchemaReaderTest {
    String testSourcesDir = "src/test/java/";

    private String getTestDataDir() {
        return testSourcesDir + getPackageName().replace(".", "/") + "/grammar/data/";
    }

    private String getPackageName() {
        return this.getClass().getPackage().getName();
    }

    private String getFilepath(String filename) {
        return getTestDataDir() + filename;
    }

    @Test
    public void testCreateTable() {
        TSqlSchemaReader schemaReader = new TSqlSchemaReader();
        DataModel dataModel = schemaReader.fromFile(getFilepath("create_table.sql"));

        assertEquals(dataModel.getEntities().size(), 1);

        Entity entity = dataModel.getEntities().get(0);
        assertEquals(entity.getFields().size(), 9);
        assertEquals("TableName", entity.getName());

        List<Field> fields = entity.getFields();

        assertEquals("Id", fields.get(0).getName());
        assertEquals("IntField", fields.get(1).getName());

        assertTrue(fields.get(0) instanceof IntegerField);
        assertTrue(fields.get(2) instanceof VarCharField);
        assertTrue(fields.get(6) instanceof DateTimeField);
        assertTrue(fields.get(7) instanceof BooleanField);
    }

    @Test
    public void testCreateTables() {
        TSqlSchemaReader schemaReader = new TSqlSchemaReader();
        DataModel dataModel = schemaReader.fromFile(getFilepath("create_tables.sql"));

        assertEquals(dataModel.getEntities().size(), 2);

        Entity entity1 = dataModel.getEntities().get(0);
        Entity entity2 = dataModel.getEntities().get(1);
        assertEquals(entity1.getFields().size(), 4);
        assertEquals(entity2.getFields().size(), 5);
        assertEquals("TableName", entity1.getName());
        assertEquals("Table2Name", entity2.getName());

        List<Field> fields1 = entity1.getFields();
        List<Field> fields2 = entity2.getFields();

        assertEquals("Id", fields1.get(0).getName());
        assertEquals("IntField", fields1.get(1).getName());
        assertEquals("BitField", fields2.get(3).getName());

        assertTrue(fields1.get(0) instanceof IntegerField);
        assertTrue(fields1.get(2) instanceof VarCharField);
        assertTrue(fields2.get(2) instanceof DateTimeField);
        assertTrue(fields2.get(3) instanceof BooleanField);
    }

    /* Test succeeds with exported SQL Server schema, but is not committed. Be aware that words like
     * MAX, GETDATE() and GETSYSDATETIMEOFFSET() should be uppercase (in the file they are
     * lowercase). Also, at some places DEFAULT ((0)) and DEFAULT ((1)) occur. These should be
     * replaced with DEFAULT (0) and (1) resp.
     */
//    @Test
//    public void testWholeFile() {
//        TSqlSchemaReader schemaReader = new TSqlSchemaReader();
//        DataModel dataModel = schemaReader.fromFile(getFilepath("IntBIQH_Schema.sql"));
//        assertEquals(dataModel.getEntities().size(), 40);
//    }
}