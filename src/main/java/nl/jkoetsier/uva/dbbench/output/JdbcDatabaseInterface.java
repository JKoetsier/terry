package nl.jkoetsier.uva.dbbench.output;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcDatabaseInterface implements DatabaseInterface {

  private Connection connection;

  protected abstract String getConnectionString();
  private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseInterface.class);


  @Override
  public void connect() {
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

  private Connection getConnection() {
    if (connection != null) {
      return connection;
    }

    try {
      connection = DriverManager.getConnection(getConnectionString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;
  }

  @Override
  public void executeQuery(String query) {
    Connection connection = getConnection();

    try {
      Statement statement = connection.createStatement();
      statement.execute(query);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
