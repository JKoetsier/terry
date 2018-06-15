package nl.jkoetsier.uva.dbbench.connector.monetdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.monetdb.schema.MonetDbSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.monetdb.workload.MonetDbWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MonetDbDatabaseConnector.class);

  public MonetDbDatabaseConnector() {
    try {
      Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected String getConnectionString() {
    String connectionString = String.format("jdbc:monetdb://%s:%s/%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase()
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  protected Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(getConnectionString(),
          dbConfigProperties.getUsername(),
          dbConfigProperties.getPassword()
      );
    }

    return connection;
  }

  @Override
  public HashMap<String, String> getCreateQueries(Schema schema) {
    MonetDbSchemaVisitor schemaVisitor = new MonetDbSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public HashMap<String, String> getWorkloadQueries(Workload workload) {
    MonetDbWorkloadVisitor workloadVisitor = new MonetDbWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  public String getSimpleName() {
    return "monetdb";
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws SQLException {
    String query = String.format("COPY OFFSET %d INTO \"%s\" FROM '%s' "
            + "USING DELIMITERS ',', '\\n', '\"' NULL AS ''",
        applicationConfigProperties.getCsvHeader() ? 2 : 1,
        tableName, file);

    executeQuery(query);
  }

  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {

  }
}
