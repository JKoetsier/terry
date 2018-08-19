package nl.jkoetsier.terry.connector.mysql;

import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.terry.connector.mysql.schema.MySqlSchemaVisitor;
import nl.jkoetsier.terry.connector.JdbcDatabaseConnector;
import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.mysql.workload.MySqlWorkloadVisitor;
import nl.jkoetsier.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.terry.connector.util.valuetranslator.DateTimeValueTranslator;
import nl.jkoetsier.terry.connector.util.valuetranslator.RemoveLineBreaksValueTranslator;
import nl.jkoetsier.terry.intrep.QueryResult;
import nl.jkoetsier.terry.intrep.SqlQuery;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseConnector.class);

  @Override
  protected String getConnectionString() {
    String otherProperties = "&useSSL=false&allowPublicKeyRetrieval=true";

    String connectionString = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase(),
        dbConfigProperties.getUsername(),
        dbConfigProperties.getPassword(),
        otherProperties
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MySqlIdentifierQuoter();
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws DatabaseException {
    SqlQuery query = new SqlQuery(String.format("LOAD DATA INFILE '%s' INTO TABLE `%s` "
        + "CHARACTER SET 'utf8' "
        + "FIELDS TERMINATED BY '%s' "
        + "ENCLOSED BY '%s' "
        + "LINES TERMINATED BY '%s' "
        + "IGNORE %d LINES",
        file,
        tableName,
        dsvConfigProperties.getFieldSeparator(),
        dsvConfigProperties.getQuote(),
        dsvConfigProperties.getRecordTerminator(),
        dsvConfigProperties.getHeaderLines()
    )
    );

    executeQuery(query);
  }

  @Override
  public HashMap<String, SqlQuery> getCreateQueries(Schema schema) {
    MySqlSchemaVisitor schemavisitor = new MySqlSchemaVisitor();
    schema.acceptVisitor(schemavisitor);

    return schemavisitor.getCreateQueries();
  }

  @Override
  public String getSimpleName() {
    return "mysql";
  }

  @Override
  public List<SqlQuery> getWorkloadQueries(Workload workload) {
    MySqlWorkloadVisitor workloadVisitor = new MySqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {
    queryResult.replaceValues("false", "0");
    queryResult.replaceValues("true", "1");
    queryResult.replaceValues(new RemoveLineBreaksValueTranslator());
    expectedResult.replaceValues(new DateTimeValueTranslator());
  }
}
