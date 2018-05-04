package nl.jkoetsier.uva.dbbench.connector.postgres.workload;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresWorkloadQueryGenerator {

  private static Logger logger = LoggerFactory.getLogger(PostgresWorkloadQueryGenerator.class);

  public HashMap<Integer, String> generateQueries(Workload workload) {
    PostgresWorkloadVisitor workloadVisitor = new PostgresWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
