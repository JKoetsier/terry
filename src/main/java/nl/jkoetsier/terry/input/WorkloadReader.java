package nl.jkoetsier.terry.input;

import nl.jkoetsier.terry.intrep.workload.Workload;

public interface WorkloadReader {

  Workload fromFile(String fileName);

  Workload fromString(String sql);
}
