package nl.jkoetsier.uva.terry.connector.monetdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.terry.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.connector.monetdb.schema.MonetDbSchemaVisitor;
import nl.jkoetsier.uva.terry.connector.monetdb.workload.MonetDbWorkloadVisitor;
import nl.jkoetsier.uva.terry.connector.util.csvlayout.CsvLayout;
import nl.jkoetsier.uva.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.terry.connector.util.valuetranslator.RemoveLineBreaksValueTranslator;
import nl.jkoetsier.uva.terry.intrep.QueryResult;
import nl.jkoetsier.uva.terry.intrep.SqlQuery;
import nl.jkoetsier.uva.terry.intrep.schema.Schema;
import nl.jkoetsier.uva.terry.intrep.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MonetDbDatabaseConnector.class);

  public MonetDbDatabaseConnector() {
    try {
      Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected String getConnectionString() {
    String connectionString = String.format("jdbc:monetdb://%s:%s/%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase()
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MonetDbIdentifierQuoter();
  }

  @Override
  protected Connection getConnection() throws DatabaseException {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(getConnectionString(),
            dbConfigProperties.getUsername(),
            dbConfigProperties.getPassword()
        );
      } catch (SQLException e) {
        throw new DatabaseException(e);
      }
    }

    return connection;
  }

  @Override
  public HashMap<String, SqlQuery> getCreateQueries(Schema schema) {
    MonetDbSchemaVisitor schemaVisitor = new MonetDbSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public List<SqlQuery> getWorkloadQueries(Workload workload) {
    MonetDbWorkloadVisitor workloadVisitor = new MonetDbWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  public String getSimpleName() {
    return "monetdb";
  }

  @Override
  protected void importCsvFile(String tableName, String file, CsvLayout csvLayout) throws DatabaseException {
    SqlQuery query = new SqlQuery(String.format("COPY OFFSET %d INTO \"%s\" FROM '%s' "
            + "USING DELIMITERS '%s', '%s', '%s' NULL AS ''",
        csvLayout.hasHeader() ? 2 : 1,
        tableName,
        file,
        csvLayout.getFieldSeparator(),
        csvLayout.getLineTerminator(),
        csvLayout.getFieldEnclosing()
    ));

    executeQuery(query);
  }

  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {
    queryResult.replaceValues("false", "0");
    queryResult.replaceValues("true", "1");
    queryResult.replaceValues(new RemoveLineBreaksValueTranslator());
  }

  @Override
  public String getCreateIndexQuery(String tableName, String columnName, Direction direction) {
    return String.format("CREATE INDEX %s_%s_%s ON %s (%s)",
        tableName,
        columnName,
        direction.toString().toLowerCase(),
        tableName,
        columnName
    );
  }
}
