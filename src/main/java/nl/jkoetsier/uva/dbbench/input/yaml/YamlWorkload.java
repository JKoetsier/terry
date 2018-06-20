package nl.jkoetsier.uva.dbbench.input.yaml;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlWorkload {

  private static Logger logger = LoggerFactory.getLogger(YamlWorkload.class);

  private List<YamlQuery> workload;

  public List<YamlQuery> getWorkload() {
    return workload;
  }

  public void setWorkload(List<YamlQuery> workload) {
    this.workload = workload;
  }
}
