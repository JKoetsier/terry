package nl.jkoetsier.uva.dbbench.config;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class ApplicationConfigProperties {

  private static Logger logger = LoggerFactory.getLogger(ApplicationConfigProperties.class);

  private List<String> acceptedDatabases;
  private int noRuns;
  private int skipFirst;
  private int defaultPort;
  private boolean csvHeader = false;

  public List<String> getAcceptedDatabases() {
    return acceptedDatabases;
  }

  public void setAcceptedDatabases(List<String> acceptedDatabases) {
    this.acceptedDatabases = acceptedDatabases;
  }

  public int getSkipFirst() {
    return skipFirst;
  }

  public void setSkipFirst(int skipFirst) {
    this.skipFirst = skipFirst;
  }

  public int getNoRuns() {
    return noRuns;
  }

  public void setNoRuns(int noRuns) {
    this.noRuns = noRuns;
  }

  public Integer getDefaultPort() {
    return defaultPort;
  }

  public void setDefaultPort(Integer defaultPort) {
    this.defaultPort = defaultPort;
  }

  public boolean getCsvHeader() {
    return csvHeader;
  }

  public void setCsvHeader(boolean csvHeader) {
    this.csvHeader = csvHeader;
  }
}
