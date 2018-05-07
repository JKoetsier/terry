package nl.jkoetsier.uva.dbbench.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcDatabaseConnector implements DatabaseConnector {

  protected Connection connection;
  protected DbConfigProperties dbConfigProperties;

  protected abstract String getConnectionString();

  private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseConnector.class);

  public JdbcDatabaseConnector(DbConfigProperties dbConfigProperties) {
    this.dbConfigProperties = dbConfigProperties;
  }

  @Override
  public void connect() throws SQLException {
    getConnection();
  }

  @Override
  public boolean isDocker() {
    return dbConfigProperties.isDocker();
  }

  @Override
  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected Connection getConnection() throws SQLException {
    if (connection != null) {
      return connection;
    }

    connection = DriverManager.getConnection(getConnectionString());

    return connection;
  }

  @Override
  public void executeQuery(String query) throws SQLException {
    Connection connection = getConnection();

    try {
      logger.debug("Query: {}", query);

      Statement statement = connection.createStatement();
      statement.execute(query);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void importSchema(Schema schema) throws SQLException {
    HashMap<String, String> createQueries = getCreateQueries(schema);

    logger.info(String.format("Start import of %d tables", createQueries.size()));

    for (Entry<String, String> queryEntrySet : createQueries.entrySet()) {
      logger.info(String.format("Creating table %s", queryEntrySet.getKey()));

      executeQuery(queryEntrySet.getValue());
    }
  }
}
