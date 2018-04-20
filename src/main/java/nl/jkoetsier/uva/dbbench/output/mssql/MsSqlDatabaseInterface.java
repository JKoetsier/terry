package nl.jkoetsier.uva.dbbench.output.mssql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.output.DatabaseInterface;
import nl.jkoetsier.uva.dbbench.output.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlDatabaseInterface implements DatabaseInterface {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
  private Connection connection;

  private HashMap<String, String> getCreateQueries(Schema schema) {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  // TODO extract
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

  @Override
  public void connect() {
    getConnection();
  }

  @Override
  public void start() {
    // TODO check and stuff. Test, do stuff
    executeCommand("docker stop sqlserver");
    executeCommand("docker rm sqlserver");
  }

  private String executeCommand(String command) {
    StringBuffer output = new StringBuffer();
    Process process;

    try {
      process = Runtime.getRuntime().exec(command);
      process.waitFor();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;

      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return output.toString();
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

  @Override
  public void importSchema(Schema schema) {
    HashMap<String, String> createQueries = getCreateQueries(schema);

    logger.info(String.format("Start import of %d tables", createQueries.size()));

    for (Entry<String, String> queryEntrySet : createQueries.entrySet()) {
      logger.info(String.format("Creating table %s", queryEntrySet.getKey()));

      executeQuery(queryEntrySet.getValue());
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
}
