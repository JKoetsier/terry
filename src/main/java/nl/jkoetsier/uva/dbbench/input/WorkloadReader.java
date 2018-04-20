package nl.jkoetsier.uva.dbbench.input;

import nl.jkoetsier.uva.dbbench.internal.workload.Workload;

public interface WorkloadReader {

  Workload fromFile(String fileName);

  Workload fromString(String sql);
}
