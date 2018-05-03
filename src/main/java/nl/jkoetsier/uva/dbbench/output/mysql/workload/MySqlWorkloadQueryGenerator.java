package nl.jkoetsier.uva.dbbench.output.mysql.workload;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlWorkloadQueryGenerator {

  private static Logger logger = LoggerFactory.getLogger(MySqlWorkloadQueryGenerator.class);

  public HashMap<Integer, String> generateQueries(Workload workload) {
    MySqlWorkloadVisitor workloadVisitor = new MySqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
