package nl.jkoetsier.uva.terry.input;

import nl.jkoetsier.uva.terry.intrep.workload.Workload;

public interface WorkloadReader {

  Workload fromFile(String fileName);

  Workload fromString(String sql);
}
