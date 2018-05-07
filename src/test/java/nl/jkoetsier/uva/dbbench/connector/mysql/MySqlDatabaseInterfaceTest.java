package nl.jkoetsier.uva.dbbench.connector.mysql;

import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;

public class MySqlDatabaseInterfaceTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Schema getSchemaFromFile(String filename) {
    SqlSchemaReader reader = new SqlSchemaReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }
//
//  @Test
//  public void testGetCreateQueries() {
//    Schema schema = getSchemaFromFile("create_table.sql");
//
//    MySqlDatabaseConnector databaseInterface = new MySqlDatabaseConnector(new MySqlConfigProperties());
//
//    HashMap<String, String> myQueries = databaseInterface.getCreateQueries(schema);
//
//    System.out.println(myQueries.get("TableName"));
//
//    MsSqlDatabaseConnector msSqlDatabaseInterface = new MsSqlDatabaseConnector(new MsSqlConfigProperties());
//    HashMap<String, String> msQueries = msSqlDatabaseInterface.getCreateQueries(schema);
//
//    System.out.println(msQueries.get("TableName"));
//  }
}