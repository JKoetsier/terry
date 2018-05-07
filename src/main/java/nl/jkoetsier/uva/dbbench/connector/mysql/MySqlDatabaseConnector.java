package nl.jkoetsier.uva.dbbench.connector.mysql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.mysql.schema.MySqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.workload.MySqlWorkloadQueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseConnector.class);

  public MySqlDatabaseConnector(DbConfigProperties dbConfigProperties) {
    super(dbConfigProperties);
  }

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
  public HashMap<String, String> getCreateQueries(Schema schema) {
    MySqlSchemaVisitor schemavisitor = new MySqlSchemaVisitor();
    schema.acceptVisitor(schemavisitor);

    return schemavisitor.getCreateQueries();
  }
  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    MySqlWorkloadQueryGenerator queryGenerator = new MySqlWorkloadQueryGenerator();

    return queryGenerator.generateQueries(workload);
  }
}
