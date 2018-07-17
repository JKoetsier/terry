package nl.jkoetsier.uva.terry.config;

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
  private String outputDirectory;
  private boolean skipResultValidation = false;
  private boolean renameImportedCsvFiles = false;
  private String dbType = "";
  private String workload = "";
  private String schema = "";
  private String dataDirectory = "";
  private boolean stopContainer = true;
  private boolean checkWorkload = false;
  private boolean skipCreateSchema = false;
  private boolean createIndices = false;

  public boolean createIndices() {
    return createIndices;
  }

  public void setCreateIndices(boolean createIndices) {
    this.createIndices = createIndices;
  }

  public String getDbType() {
    return dbType;
  }

  public void setDbType(String dbType) {
    this.dbType = dbType;
  }

  public String getWorkload() {
    return workload;
  }

  public void setWorkload(String workload) {
    this.workload = workload;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getDataDirectory() {
    return dataDirectory;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public boolean isStopContainer() {
    return stopContainer;
  }

  public void setStopContainer(boolean stopContainer) {
    this.stopContainer = stopContainer;
  }

  public boolean isCheckWorkload() {
    return checkWorkload;
  }

  public void setCheckWorkload(boolean checkWorkload) {
    this.checkWorkload = checkWorkload;
  }

  public boolean isSkipCreateSchema() {
    return skipCreateSchema;
  }

  public void setSkipCreateSchema(boolean skipCreateSchema) {
    this.skipCreateSchema = skipCreateSchema;
  }

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

  public boolean skipResultValidation() {
    return skipResultValidation;
  }

  public void setSkipResultValidation(boolean skipResultValidation) {
    this.skipResultValidation = skipResultValidation;
  }

  public boolean renameImportedCsvFiles() {
    return renameImportedCsvFiles;
  }

  public void setRenameImportedCsvFiles(boolean renameImportedCsvFiles) {
    this.renameImportedCsvFiles = renameImportedCsvFiles;
  }

  public String getOutputDirectory() {
    return outputDirectory;
  }

  public void setOutputDirectory(String outputDirectory) {
    if (outputDirectory.charAt(outputDirectory.length() - 1) == '/') {
      this.outputDirectory = outputDirectory.substring(0, outputDirectory.length() - 1);
    } else {
      this.outputDirectory = outputDirectory;
    }
  }
}
