package nl.jkoetsier.uva.dbbench.input;

import nl.jkoetsier.uva.dbbench.workload.Workload;

public interface WorkloadReader {

  Workload fromFile(String fileName);

  Workload fromString(String sql);
}
