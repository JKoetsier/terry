package nl.jkoetsier.uva.dbbench.connector.mssql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.connector.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mssql.workload.MsSqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.SqlQuery;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlDatabaseConnector extends JdbcDatabaseConnector {

  protected static Logger logger = LoggerFactory.getLogger(MsSqlDatabaseConnector.class);

  @Override
  public HashMap<String, SqlQuery> getCreateQueries(Schema schema) {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public HashMap<String, SqlQuery> getWorkloadQueries(Workload workload) {
    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }

  @Override
  public String getSimpleName() {
    return "mssql";
  }

  @Override
  protected String getConnectionString() {
    String connectString = String.format("jdbc:sqlserver://%s:%s;"
            + "database=%s;"
            + "user=%s;"
            + "password=%s;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;",
        dbConfigProperties.getHost(),
        dbConfigProperties.getPort(),
        dbConfigProperties.getDatabase(),
        dbConfigProperties.getUsername(),
        dbConfigProperties.getPassword()
    );

    logger.debug("Connection String: {}", connectString);

    return connectString;
  }

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MsSqlIdentifierQuoter();
  }

  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {

  }

  @Override
  protected void importCsvFile(String tableName, String file) throws DatabaseException {
    SqlQuery query = new SqlQuery(String.format("BULK INSERT [%s] FROM '%s' WITH (FIRSTROW = %d)", tableName, file,
        applicationConfigProperties.getCsvHeader() ? 2 : 1));

    executeQuery(query);
  }
}