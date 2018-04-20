package nl.jkoetsier.uva.dbbench.output.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.output.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlRunner {

  private Schema schema = Schema.getInstance();
  private Connection connection;

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  private HashMap<String, String> getCreateQueries() {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  public void setupDatabase() {
    importSchema();
  }

  private String getConnectionString() {
    return "jdbc:sqlserver://127.0.0.1:1401;"
        + "database=benchtest;"
        + "user=SA;"
        + "password=Qwerty123!;"
        + "encrypt=true;"
        + "trustServerCertificate=true;"
        + "hostNameInCertificate=*.database.windows.net;"
        + "loginTimeout=30;";
  }

  private void closeConnection() {
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

  private void executeQuery(String query) {
    Connection connection = getConnection();

    try {
      Statement statement = connection.createStatement();
      statement.execute(query);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("DB PROBS Bro");
    }
  }

  private void importSchema() {
    HashMap<String, String> createQueries = getCreateQueries();

    logger.info(String.format("Start import of %d tables", createQueries.size()));

    for (Entry<String, String> queryEntrySet : createQueries.entrySet()) {
      logger.info(String.format("Creating table %s", queryEntrySet.getKey()));

      executeQuery(queryEntrySet.getValue());
    }

    closeConnection();
  }
}
