package nl.jkoetsier.uva.dbbench.output.mssql.workload;

import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public class MsSqlWorkloadQueryGenerator {

  public List<String> generateQueries(Workload workload) {
    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }
}
