package nl.jkoetsier.uva.dbbench.connector;

import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public interface DatabaseInterface {

  void connect() throws SQLException;
  void executeQuery(String query) throws SQLException;
  void importSchema(Schema schema) throws SQLException;
  void closeConnection();
  boolean isDocker();
  DbConfigProperties getConfigProperties();
  HashMap<Integer, String> getWorkloadQueries(Workload workload);
}
