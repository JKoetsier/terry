package nl.jkoetsier.uva.terry.connector;

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
import nl.jkoetsier.uva.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.terry.internal.ExecutableQuery;
import nl.jkoetsier.uva.terry.internal.QueryResult;
import nl.jkoetsier.uva.terry.internal.QueryResultRow;
import nl.jkoetsier.uva.terry.internal.SqlQuery;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class JdbcDatabaseConnector extends DatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseConnector.class);
  private Statement lastStatement;

  protected Connection connection;

  protected abstract String getConnectionString();
  protected abstract SqlIdentifierQuoter getIdentifierQuoter();
  public abstract HashMap<String, SqlQuery> getCreateQueries(Schema schema);

  @Override
  public void connect() throws DatabaseException {
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

  protected Connection getConnection() throws DatabaseException {
    if (connection != null) {
      return connection;
    }

    try {
      connection = DriverManager.getConnection(getConnectionString());
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }

    return connection;
  }

  @Override
  public void executeQuery(ExecutableQuery query) throws DatabaseException {
    assert(query instanceof SqlQuery);

    Connection connection = getConnection();

    logger.debug("Query: {}", query);

    try {
      lastStatement = connection.createStatement();
      lastStatement.execute(((SqlQuery) query).getQueryString());
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public QueryResult getLastResults() throws DatabaseException {
    if (lastStatement != null) {
      ResultSet resultSet = null;

      try {
        resultSet = lastStatement.getResultSet();
      } catch (SQLException e) {
        throw new DatabaseException(e);
      }

      return getResults(resultSet);
    }

    return null;
  }

  private String[] getColumnNames(ResultSet resultSet) throws DatabaseException {
    try {
      ResultSetMetaData metaData = resultSet.getMetaData();

      int columnCount = metaData.getColumnCount();
      String[] columns = new String[columnCount];

      for (int i = 1; i <= columnCount; i++) {
        columns[i - 1] = metaData.getColumnName(i);
      }

      return columns;
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  private QueryResult getResults(ResultSet resultSet) throws DatabaseException {
    QueryResult queryResult = new QueryResult();
    List<QueryResultRow> resultRowList = new ArrayList<>();

    String[] columns = getColumnNames(resultSet);
    int columnCount = columns.length;

    try {
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
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }

    queryResult.setRows(resultRowList);
    return queryResult;
  }

  @Override
  public void importSchema(Schema schema) throws DatabaseException {
    HashMap<String, SqlQuery> createQueries = getCreateQueries(schema);

    logger.info(String.format("Start import of %d tables", createQueries.size()));

    for (Entry<String, SqlQuery> queryEntrySet : createQueries.entrySet()) {
      logger.info(String.format("Creating table %s", queryEntrySet.getKey()));

      executeQuery(queryEntrySet.getValue());
    }
  }

  @Override
  public long getTableSize(String tableName) throws DatabaseException {
    SqlQuery query = new SqlQuery(String
        .format("SELECT COUNT(*) FROM %s", getIdentifierQuoter().quoteString(tableName)));

    executeQuery(query);

    QueryResult queryResult = getLastResults();
    return Integer.valueOf(queryResult.getRows().get(0).getValues()[0]);
  }
}
