package nl.jkoetsier.uva.dbbench.output.mysql;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.MySqlConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.output.mysql.schema.MySqlSchemavisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseInterface extends JdbcDatabaseInterface {

  private static Logger logger = LoggerFactory.getLogger(MySqlDatabaseInterface.class);

  private MySqlConfigProperties configProperties;

  public MySqlDatabaseInterface(MySqlConfigProperties configProperties) {
    this.configProperties = configProperties;
  }

  @Override
  protected String getConnectionString() {
    return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
        configProperties.getHost(),
        configProperties.getPort(),
        configProperties.getDatabase(),
        configProperties.getUsername(),
        configProperties.getPassword()
    );
  }

  @Override
  protected HashMap<String, String> getCreateQueries(Schema schema) {
    MySqlSchemavisitor schemavisitor = new MySqlSchemavisitor();
    schema.acceptVisitor(schemavisitor);

    return schemavisitor.getCreateQueries();
  }

  @Override
  public HashMap<Integer, String> getWorkloadQueries(Workload workload) {
    return null;
  }
}
