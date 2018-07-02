package nl.jkoetsier.uva.terry.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:db.${outputdb:mssql}.properties")
public class DbConfigProperties {

  private String type = "";
  private String dockerImage;
  private Integer defaultDbPort;
  private String[] dockerEnvvars;
  private String dockerDataDirectory = "/opt/data";
  private String dockerReadyLogLine = "";
  private String connector;

  private String host;
  private Integer port;
  private String database;
  private String username;
  private String password;

  public String getDockerReadyLogLine() {
    return dockerReadyLogLine;
  }

  public void setDockerReadyLogLine(String dockerReadyLogLine) {
    this.dockerReadyLogLine = dockerReadyLogLine;
  }

  public String getDockerDataDirectory() {
    return dockerDataDirectory;
  }

  public void setDockerDataDirectory(String dockerDataDirectory) {
    this.dockerDataDirectory = dockerDataDirectory;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String[] getDockerEnvvars() {
    return dockerEnvvars;
  }

  public void setDockerEnvvars(String[] dockerEnvvars) {
    this.dockerEnvvars = dockerEnvvars;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public String getDockerImage() {
    return dockerImage;
  }

  public void setDockerImage(String dockerImage) {
    this.dockerImage = dockerImage;
  }

  public Integer getDefaultDbPort() {
    return defaultDbPort;
  }

  public void setDefaultDbPort(Integer defaultDbPort) {
    this.defaultDbPort = defaultDbPort;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isDocker() {
    return type.equals("docker");
  }

  public String getConnector() {
    return connector;
  }

  public void setConnector(String connector) {
    this.connector = connector;
  }
}
