package nl.jkoetsier.uva.dbbench.connector.mssql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mssql.workload.MsSqlWorkloadQueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlDatabaseConnector extends JdbcDatabaseConnector {

  protected static Logger logger = LoggerFactory.getLogger(MsSqlDatabaseConnector.class);

  public MsSqlDatabaseConnector(DbConfigProperties dbConfigProperties) {
    super(dbConfigProperties);
  }

  @Override
  public HashMap<String, String> getCreateQueries(Schema schema) {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    MsSqlWorkloadQueryGenerator queryGenerator = new MsSqlWorkloadQueryGenerator();

    return queryGenerator.generateQueries(workload);
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

}