package nl.jkoetsier.uva.dbbench.connector.mysql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.connector.mysql.schema.MySqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.workload.MySqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.connector.util.valuetranslator.DateTimeValueTranslator;
import nl.jkoetsier.uva.dbbench.connector.util.valuetranslator.RemoveLineBreaksValueTranslator;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.SqlQuery;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseConnector.class);

  @Override
  protected String getConnectionString() {
    String otherProperties = "&useSSL=false";

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
        + "FIELDS TERMINATED BY ',' "
        + "ENCLOSED BY '\"' "
        + "LINES TERMINATED BY '\\n' "
        + "IGNORE %d LINES", file, tableName, applicationConfigProperties.getCsvHeader() ? 1 : 0));

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
  public HashMap<String, SqlQuery> getWorkloadQueries(Workload workload) {
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
