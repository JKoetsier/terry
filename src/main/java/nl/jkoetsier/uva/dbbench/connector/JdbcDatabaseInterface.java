package nl.jkoetsier.uva.dbbench.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcDatabaseInterface implements DatabaseInterface {

  private static Integer MAX_TRIES = 10;

  protected Connection connection;

  protected abstract String getConnectionString();
  protected abstract HashMap<String, String> getCreateQueries(Schema schema);

  private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseInterface.class);


  @Override
  public void connect() throws SQLException {
    getConnection();
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

  private Connection getConnection() throws SQLException {
    if (connection != null) {
      return connection;
    }

    int tries = 0;

    while (connection == null) {
      try {
        connection = DriverManager.getConnection(getConnectionString());
      } catch (SQLException e) {
        tries++;
        logger.info("Failed to connect to database ({})", tries);

        if (tries >= MAX_TRIES) {
          throw e;
        }

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {

        }
      }
    }

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
