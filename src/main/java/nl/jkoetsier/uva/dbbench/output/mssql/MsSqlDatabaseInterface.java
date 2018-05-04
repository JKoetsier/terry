package nl.jkoetsier.uva.dbbench.output.mssql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.output.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.output.mssql.workload.MsSqlWorkloadQueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlDatabaseInterface extends JdbcDatabaseInterface {

  private DbConfigProperties configProperties;

  protected static Logger logger = LoggerFactory.getLogger(MsSqlDatabaseInterface.class);

  public MsSqlDatabaseInterface(DbConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @Override
  public HashMap<String, String> getCreateQueries(Schema schema) {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  public boolean isDocker() {
    return configProperties.isDocker();
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
        configProperties.getHost(),
        configProperties.getPort(),
        configProperties.getDatabase(),
        configProperties.getUsername(),
        configProperties.getPassword()
    );

    logger.debug("Connection String: {}", connectString);

    return connectString;
  }


  @Override
  public DbConfigProperties getConfigProperties() {
    return configProperties;
  }
}