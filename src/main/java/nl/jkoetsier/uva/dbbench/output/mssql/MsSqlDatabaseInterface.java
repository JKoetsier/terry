package nl.jkoetsier.uva.dbbench.output.mssql;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.output.JdbcDatabaseInterface;
import nl.jkoetsier.uva.dbbench.output.mssql.schema.MsSqlSchemaVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class MsSqlDatabaseInterface extends JdbcDatabaseInterface {

  @Value("${mssql.host}")
  private String host;

  @Value("${mssql.port}")
  private String port;

  @Value("${mssql.user}")
  private String user;

  @Value("${mssql.password}")
  private String password;

  @Value("${mssql.database}")
  private String database;

  private static Logger logger = LoggerFactory.getLogger(MsSqlDatabaseInterface.class);

  private HashMap<String, String> getCreateQueries(Schema schema) {
    MsSqlSchemaVisitor schemaVisitor = new MsSqlSchemaVisitor();
    schema.acceptVisitor(schemaVisitor);

    return schemaVisitor.getCreateQueries();
  }

  @Override
  protected String getConnectionString() {
    return String.format("jdbc:sqlserver://%s:%s;"
            + "database=%s;"
            + "user=%s;"
            + "password=%s;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;",
        host,
        port,
        database,
        user,
        password
    );
  }


  @Override
  public void importSchema(Schema schema) {
    HashMap<String, String> createQueries = getCreateQueries(schema);

    logger.info(String.format("Start import of %d tables", createQueries.size()));

    for (Entry<String, String> queryEntrySet : createQueries.entrySet()) {
      logger.info(String.format("Creating table %s", queryEntrySet.getKey()));

      executeQuery(queryEntrySet.getValue());
    }
  }


  @Override
  public void dropTable(String table) {

  }

}
