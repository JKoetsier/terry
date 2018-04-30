package nl.jkoetsier.uva.dbbench.output.mssql;

import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.dbbench.config.MsSqlConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.output.mssql.schema.MsSqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.output.mssql.workload.MsSqlWorkloadQueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlDatabaseInterface extends JdbcDatabaseInterface {

  private MsSqlConfigProperties configProperties;

  private static Logger logger = LoggerFactory.getLogger(MsSqlDatabaseInterface.class);

  public MsSqlDatabaseInterface(MsSqlConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  protected HashMap<String, String> getCreateQueries(Schema schema) {
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
        configProperties.getHost(),
        configProperties.getPort(),
        configProperties.getDatabase(),
        configProperties.getUsername(),
        configProperties.getPassword()
    );

    logger.debug("Connection String: {}", connectString);

    return connectString;
  }


}