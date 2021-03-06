package nl.jkoetsier.terry.connector.postgres;

import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.postgres.workload.PostgresWorkloadVisitor;
import nl.jkoetsier.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.terry.connector.util.valuetranslator.DateTimeValueTranslator;
import nl.jkoetsier.terry.connector.util.valuetranslator.RemoveLineBreaksValueTranslator;
import nl.jkoetsier.terry.intrep.QueryResult;
import nl.jkoetsier.terry.intrep.SqlQuery;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.connector.JdbcDatabaseConnector;
import nl.jkoetsier.terry.connector.postgres.schema.PostgresSchemaVisitor;
import nl.jkoetsier.terry.connector.util.exception.UnsupportedConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresDatabaseConnector extends JdbcDatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(PostgresDatabaseConnector.class);

  @Override
  protected String getConnectionString() {
    String properties = String.format("user=%s&password=%s&ssl=false",
        dbConfigProperties.getUsername(),
        dbConfigProperties.getPassword()
    );

    String connectionString = String.format("jdbc:postgresql://%s:%s/%s?%s",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase(),
        properties
    );

    logger.debug("Connection string: {}", connectionString);

    return connectionString;
  }

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new PostgresIdentifierQuoter();
  }

  @Override
  public HashMap<String, SqlQuery> getCreateQueries(Schema schema) {
    PostgresSchemaVisitor schemaVisitor = new PostgresSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public String getSimpleName() {
    return "postgres";
  }


  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {
    queryResult.replaceValues("false", "0");
    queryResult.replaceValues("true", "1");
    queryResult.replaceValues(new RemoveLineBreaksValueTranslator());
    expectedResult.replaceValues(new DateTimeValueTranslator());
  }

  @Override
  public List<SqlQuery> getWorkloadQueries(Workload workload) {
    PostgresWorkloadVisitor workloadVisitor = new PostgresWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws DatabaseException {
    if (dsvConfigProperties.getHeaderLines() > 1) {
      throw new UnsupportedConfigurationException(
          "Postgres does not support multiple header lines");
    }

    SqlQuery query = new SqlQuery(String.format("COPY \"%s\" FROM '%s' "
            + "WITH (FORMAT csv, %sDELIMITER '%s'%s)",
        tableName,
        file,
        dsvConfigProperties.getHeaderLines() > 0 ? "HEADER true, " : "",
        dsvConfigProperties.getFieldSeparator(),
        dsvConfigProperties.getNullValue().equals("") ? ""
            : String.format(", NULL '%s'", dsvConfigProperties.getNullValue())
    ));

    executeQuery(query);
  }
}
