package nl.jkoetsier.uva.dbbench.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class CommandLineConfigProperties {

  private static Logger logger = LoggerFactory.getLogger(CommandLineConfigProperties.class);

  private String outputDb;
  private String workload;
  private String datamodel;

  public String getOutputDb() {
    return outputDb;
  }

  public void setOutputDb(String outputDb) {
    this.outputDb = outputDb;
  }

  public String getWorkload() {
    return workload;
  }

  public void setWorkload(String workload) {
    this.workload = workload;
  }

  public String getDatamodel() {
    return datamodel;
  }

  public void setDatamodel(String datamodel) {
    this.datamodel = datamodel;
  }
}