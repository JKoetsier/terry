package nl.jkoetsier.uva.dbbench.connector;

import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DatabaseConnector {
  public abstract void connect() throws SQLException;

  public abstract void executeQuery(String query) throws SQLException;

  public abstract void importSchema(Schema schema) throws SQLException;

  public abstract void importCsvData(String directory) throws SQLException;

  public abstract void closeConnection();

  public abstract HashMap<Integer, String> getWorkloadQueries(Workload workload);

  public abstract HashMap<String, String> getCreateQueries(Schema schema);

  @Autowired
  protected DbConfigProperties dbConfigProperties;

  public boolean isDocker() {
    return dbConfigProperties.isDocker();
  }
}
