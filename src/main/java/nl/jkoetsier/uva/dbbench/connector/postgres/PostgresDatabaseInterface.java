package nl.jkoetsier.uva.dbbench.connector.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.connector.postgres.schema.PostgresSchemaVisitor;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresDatabaseInterface extends JdbcDatabaseInterface {

  private static Logger logger = LoggerFactory.getLogger(PostgresDatabaseInterface.class);

  private DbConfigProperties dbConfigProperties;

  public PostgresDatabaseInterface(DbConfigProperties dbConfigProperties) {
    this.dbConfigProperties = dbConfigProperties;
  }

  @Override
  public boolean isDocker() {
    return dbConfigProperties.isDocker();
  }

  @Override
  public DbConfigProperties getConfigProperties() {
    return dbConfigProperties;
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
  protected HashMap<String, String> getCreateQueries(Schema schema) {
    PostgresSchemaVisitor schemaVisitor = new PostgresSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    return null;
  }
}
