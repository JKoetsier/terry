package nl.jkoetsier.uva.dbbench.connector.postgres;

import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.postgres.schema.PostgresSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.postgres.workload.PostgresWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(PostgresDatabaseConnector.class);

  @Override
  protected String getConnectionString() {
    String properties = String.format("user=%s&password=%s&ssl=false",
        dbConfigProperties.getUsername(),
        dbConfigProperties.getPassword()
    );

    String connectionString = String.format("jdbc:postgresql://%s:%s/%s?%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase(),
        properties
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  public HashMap<String, String> getCreateQueries(Schema schema) {
    PostgresSchemaVisitor schemaVisitor = new PostgresSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    PostgresWorkloadVisitor workloadVisitor = new PostgresWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws SQLException {
    String query = String.format("COPY \"%s\" FROM '%s' "
        + "WITH (FORMAT csv, %sDELIMITER ',', NULL 'NULL')", tableName, file,
        applicationConfigProperties.getCsvHeader() ? "HEADER true, " : "");

    executeQuery(query);
  }
}
