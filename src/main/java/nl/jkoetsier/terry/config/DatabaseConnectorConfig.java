package nl.jkoetsier.terry.config;

import nl.jkoetsier.terry.connector.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConnectorConfig {

  private static Logger logger = LoggerFactory.getLogger(DatabaseConnectorConfig.class);

  @Autowired
  private DbConfigProperties dbConfigProperties;

  @Bean
  public DatabaseConnector databaseConnector()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {

    logger.debug("Linking DatabaseConnector {}", dbConfigProperties.getConnector());

    return (DatabaseConnector) Class.forName(dbConfigProperties.getConnector()).newInstance();
  }
}
