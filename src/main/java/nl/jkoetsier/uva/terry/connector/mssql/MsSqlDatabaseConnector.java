package nl.jkoetsier.uva.terry.connector.mssql;

import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.terry.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.connector.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.terry.connector.mssql.workload.MsSqlWorkloadVisitor;
import nl.jkoetsier.uva.terry.connector.util.csvlayout.CsvLayout;
import nl.jkoetsier.uva.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.terry.intrep.QueryResult;
import nl.jkoetsier.uva.terry.intrep.SqlQuery;
import nl.jkoetsier.uva.terry.intrep.schema.Schema;
import nl.jkoetsier.uva.terry.intrep.workload.Workload;
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
  public List<SqlQuery> getWorkloadQueries(Workload workload) {
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
  protected void importCsvFile(String tableName, String file, CsvLayout csvLayout) throws DatabaseException {
    SqlQuery query = new SqlQuery(String.format("BULK INSERT [%s] FROM '%s' WITH (FIRSTROW = %d, "
            + "ROWTERMINATOR = '%s', FIELDTERMINATOR = '%s'%s)", tableName, file,
        csvLayout.hasHeader() ? 2 : 1,
        csvLayout.getLineTerminator(),
        csvLayout.getFieldSeparator(),
        csvLayout.getFieldEnclosing() != null ? String.format(", FIELDQUOTE = '%s'", csvLayout.getFieldEnclosing()) : ""
    ));

    executeQuery(query);
  }
}