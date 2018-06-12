package nl.jkoetsier.uva.dbbench.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.QueryResultRow;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class JdbcDatabaseConnector extends DatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseConnector.class);

  private Statement lastStatement;
  protected Connection connection;

  protected abstract String getConnectionString();

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

      lastStatement = connection.createStatement();
      lastStatement.execute(query);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public QueryResult getLastResults() throws SQLException {
    if (lastStatement != null) {
      ResultSet resultSet = lastStatement.getResultSet();

      return getResults(resultSet);
    }

    return null;
  }

  private String[] getColumnNames(ResultSet resultSet) throws SQLException {
    ResultSetMetaData metaData = resultSet.getMetaData();

    int columnCount = metaData.getColumnCount();
    String[] columns = new String[columnCount];

    for (int i = 1; i <= columnCount; i++) {
      columns[i - 1] = metaData.getColumnName(i);
    }

    return columns;
  }

  private QueryResult getResults(ResultSet resultSet) throws SQLException {
    QueryResult queryResult = new QueryResult();
    List<QueryResultRow> resultRowList = new ArrayList<>();

    String[] columns = getColumnNames(resultSet);
    int columnCount = columns.length;

    while (resultSet.next()) {
      String[] row = new String[columnCount];

      for (int i = 1; i <= columnCount; i++) {
        if (resultSet.getObject(i) == null) {
          row[i - 1] = "NULL";
        } else {
          row[i - 1] = resultSet.getObject(i).toString();
        }
      }
      QueryResultRow resultRow = new QueryResultRow(row);
      resultRowList.add(resultRow);
    }

    queryResult.setRows(resultRowList);
    return queryResult;
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
