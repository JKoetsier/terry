package nl.jkoetsier.uva.dbbench.connector.postgres;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.postgres.schema.PostgresSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.postgres.workload.PostgresWorkloadQueryGenerator;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(PostgresDatabaseConnector.class);

  public PostgresDatabaseConnector(
      DbConfigProperties dbConfigProperties) {
    super(dbConfigProperties);
  }

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
    PostgresWorkloadQueryGenerator queryGenerator = new PostgresWorkloadQueryGenerator();

    return queryGenerator.generateQueries(workload);
  }
}
