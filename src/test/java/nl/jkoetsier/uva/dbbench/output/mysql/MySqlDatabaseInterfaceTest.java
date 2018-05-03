package nl.jkoetsier.uva.dbbench.output.mysql;

import static org.junit.Assert.*;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.MsSqlConfigProperties;
import nl.jkoetsier.uva.dbbench.config.MySqlConfigProperties;
import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.output.mssql.MsSqlDatabaseInterface;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Test;

public class MySqlDatabaseInterfaceTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Schema getSchemaFromFile(String filename) {
    SqlSchemaReader reader = new SqlSchemaReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  @Test
  public void testGetCreateQueries() {
    Schema schema = getSchemaFromFile("create_table.sql");

    MySqlDatabaseInterface databaseInterface = new MySqlDatabaseInterface(new MySqlConfigProperties());

    HashMap<String, String> myQueries = databaseInterface.getCreateQueries(schema);

    System.out.println(myQueries.get("TableName"));

    MsSqlDatabaseInterface msSqlDatabaseInterface = new MsSqlDatabaseInterface(new MsSqlConfigProperties());
    HashMap<String, String> msQueries = msSqlDatabaseInterface.getCreateQueries(schema);

    System.out.println(msQueries.get("TableName"));


  }
}