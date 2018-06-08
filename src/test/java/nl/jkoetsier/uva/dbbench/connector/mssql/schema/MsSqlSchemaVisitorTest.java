package nl.jkoetsier.uva.dbbench.connector.mssql.schema;

import static nl.jkoetsier.uva.dbbench.util.Assertions.assertQueryEquals;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nl.jkoetsier.uva.dbbench.connector.SchemaTest;
import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Test;

public class MsSqlSchemaVisitorTest implements SchemaTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Schema getSchemaFromFile(String filename) {
    SqlSchemaReader reader = new SqlSchemaReader();

    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private List<String> getCreateQueries(String filename) {
    Schema schema = getSchemaFromFile(filename);

    MsSqlSchemaVisitor sqlSchemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(sqlSchemaVisitor);

    return new ArrayList<>(sqlSchemaVisitor.getCreateQueries().values());
  }

  private String normalise(String str) {
    return testDataHelper.normalise(str);
  }

  private void compareSingleQueryFromFile(String filename, String expected) {
    List<String> result = getCreateQueries(filename);

    assertEquals(1, result.size());
    assertQueryEquals(expected, normalise(result.get(0)));
  }

  private void compareMultipleQueriesFromFile(String filename, List<String> expected) {
    Collections.reverse(expected);
    List<String> result = getCreateQueries(filename);

    assertEquals(expected.size(), result.size());

    for (int i = 0; i < expected.size(); i++) {
      assertQueryEquals(expected.get(i), normalise(result.get(i)));
    }
  }

  @Test
  public void testCreateTable() {
    String expected = "CREATE TABLE [TableName] ( [Id] int IDENTITY(1,1) NOT NULL, "
        + "[IntField] int NULL, [VarChar250Field] varchar(250) NULL, "
        + "[DecimalField] decimal(15, 6) NULL, [DateTimeOffsetField] datetimeoffset NULL, "
        + "[VarCharMaxField] varchar NULL, [DateField] date NULL, [BitField] bit NULL, "
        + "[DecimalField2] decimal(38, 15) NULL, CONSTRAINT PK_TableName "
        + "PRIMARY KEY ( [Id] ) )";

    compareSingleQueryFromFile("create_table.sql", expected);
  }

  @Test
  public void testCreateTableArguments() {
    String expected = "CREATE TABLE [TableName] ( [A] decimal(5, 2) NULL, "
        + "[B] varchar(200) NOT NULL, [C] varchar(50) NOT NULL )";

    compareSingleQueryFromFile("create_table_arguments.sql", expected);
  }

  @Test
  public void testCreateTableCompositePrimaryKey() {
    String expected = "CREATE TABLE [TableName] ( [Id] int IDENTITY(1,1) NOT NULL, "
        + "[IntField] int NULL, [VarChar250Field] varchar(250) NULL, "
        + "[DecimalField] decimal(15, 6) NULL, [DateTimeOffsetField] datetimeoffset NULL, "
        + "[VarCharMaxField] varchar NULL, [DateField] date NULL, [BitField] bit NULL, "
        + "[DecimalField2] decimal(38, 15) NULL, CONSTRAINT PK_TableName "
        + "PRIMARY KEY ( [Id], [IntField] ) )";

    compareSingleQueryFromFile("create_table_composite_primarykey.sql", expected);
  }

  @Test
  public void testCreateTableNullNotNull() {
    String expected = "CREATE TABLE [TableName] ( [A] int NOT NULL, [B] int NULL, "
        + "[C] varchar NULL, [D] varchar NOT NULL )";

    compareSingleQueryFromFile("create_table_null_not_null.sql", expected);
  }

  @Test
  public void testCreateTables() {
    List<String> expected = new ArrayList<>();
    expected.add("CREATE TABLE [TableName] ( [Id] int IDENTITY(1,1) NOT NULL, "
        + "[IntField] int NULL, [VarChar250Field] varchar(250) NULL, "
        + "[DecimalField] decimal(15, 6) NULL, "
        + "CONSTRAINT PK_TableName PRIMARY KEY ( [Id] ) )");

    expected.add("CREATE TABLE [Table2Name] ( [DateTimeOffsetField] datetimeoffset NULL, "
        + "[VarCharMaxField] varchar NULL, "
        + "[DateField] date NULL, "
        + "[BitField] bit NULL, "
        + "[DecimalField2] decimal(38, 15) NULL, "
        + "CONSTRAINT PK_Table2Name PRIMARY KEY ( [BitField] ) )");

    compareMultipleQueriesFromFile("create_tables.sql", expected);
  }
}