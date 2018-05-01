package nl.jkoetsier.uva.dbbench.output;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public interface DatabaseInterface {

  void connect();
  void executeQuery(String query);
  void importSchema(Schema schema);
  void closeConnection();
  HashMap<Integer, String> getWorkloadQueries(Workload workload);
}
