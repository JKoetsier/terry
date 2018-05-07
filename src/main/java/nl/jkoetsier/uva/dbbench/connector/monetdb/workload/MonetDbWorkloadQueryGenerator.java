package nl.jkoetsier.uva.dbbench.connector.monetdb.workload;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public class MonetDbWorkloadQueryGenerator {

  public HashMap<Integer, String> generateQueries(Workload workload) {
    MonetDbWorkloadVisitor workloadVisitor = new MonetDbWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
