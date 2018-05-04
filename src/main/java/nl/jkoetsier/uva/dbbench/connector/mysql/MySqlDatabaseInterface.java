package nl.jkoetsier.uva.dbbench.connector.mysql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.connector.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.connector.mysql.schema.MySqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.workload.MySqlWorkloadQueryGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseInterface extends JdbcDatabaseInterface {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseInterface.class);

  private DbConfigProperties configProperties;

  public MySqlDatabaseInterface(DbConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @Override
  protected String getConnectionString() {
    String otherProperties = "&useSSL=false";

    return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s%s",
        configProperties.getHost(),
        configProperties.getPort(),
        configProperties.getDatabase(),
        configProperties.getUsername(),
        configProperties.getPassword(),
        otherProperties
    );
  }

  @Override
  protected HashMap<String, String> getCreateQueries(Schema schema) {
    MySqlSchemaVisitor schemavisitor = new MySqlSchemaVisitor();
    schema.acceptVisitor(schemavisitor);

    return schemavisitor.getCreateQueries();
  }

  @Override
  public boolean isDocker() {
    return configProperties.isDocker();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    MySqlWorkloadQueryGenerator queryGenerator = new MySqlWorkloadQueryGenerator();

    return queryGenerator.generateQueries(workload);
  }

  @Override
  public DbConfigProperties getConfigProperties() {
    return configProperties;
  }
}
