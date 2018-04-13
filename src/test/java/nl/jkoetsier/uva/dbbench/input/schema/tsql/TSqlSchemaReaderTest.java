package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.datamodel.Entity;
import nl.jkoetsier.uva.dbbench.datamodel.fields.*;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class TSqlSchemaReaderTest {
    String dataDirectory = "/data/tsql/";

    private String getFilepath(String filename) {
        return getClass().getResource(dataDirectory + filename).getFile();
    }

    private DataModel getDataModel(String filename) {
        TSqlSchemaReader schemaReader = new TSqlSchemaReader();
        return schemaReader.fromFile(getFilepath(filename));
    }

    @Test
    public void testCreateTable() {
        DataModel dataModel = getDataModel("create_table.sql");
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
    }

    @Test
    public void testCreateTables() {
        DataModel dataModel = getDataModel("create_tables.sql");
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