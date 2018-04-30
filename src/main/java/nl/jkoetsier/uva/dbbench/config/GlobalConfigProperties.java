package nl.jkoetsier.uva.dbbench.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class GlobalConfigProperties {

  private static Logger logger = LoggerFactory.getLogger(GlobalConfigProperties.class);

  private List<String> acceptedDatabases;
  private int noRuns;
  private int skipFirst;

  public List<String> getAcceptedDatabases() {
    return acceptedDatabases;
  }

  public void setNoRuns(int noRuns) {
    this.noRuns = noRuns;
  }

  public void setSkipFirst(int skipFirst) {
    this.skipFirst = skipFirst;
  }

  public int getSkipFirst() {
    return skipFirst;
  }

  public void setAcceptedDatabases(List<String> acceptedDatabases) {
    this.acceptedDatabases = acceptedDatabases;
  }

  public int getNoRuns() {
    return noRuns;
  }
}
