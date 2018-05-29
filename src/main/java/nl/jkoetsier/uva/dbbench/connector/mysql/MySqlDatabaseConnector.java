package nl.jkoetsier.uva.dbbench.connector.mysql;

import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.mysql.schema.MySqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.workload.MySqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseConnector.class);

  @Override
  protected String getConnectionString() {
    String otherProperties = "&useSSL=false";

    String connectionString = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase(),
        dbConfigProperties.getUsername(),
        dbConfigProperties.getPassword(),
        otherProperties
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws SQLException {
    String query = String.format("LOAD DATA INFILE '%s' INTO TABLE `%s` "
        + "FIELDS TERMINATED BY ',' "
        + "ENCLOSED BY '\"' "
        + "LINES TERMINATED BY '\\n' "
        + "IGNORE 1 LINES", file, tableName);

    executeQuery(query);
  }

  @Override
  public HashMap<String, String> getCreateQueries(Schema schema) {
    MySqlSchemaVisitor schemavisitor = new MySqlSchemaVisitor();
    schema.acceptVisitor(schemavisitor);

    return schemavisitor.getCreateQueries();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    MySqlWorkloadVisitor workloadVisitor = new MySqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
