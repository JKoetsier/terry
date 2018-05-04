package nl.jkoetsier.uva.dbbench.connector.mssql.workload;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public class MsSqlWorkloadQueryGenerator {

  public HashMap<Integer, String> generateQueries(Workload workload) {
    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
