package nl.jkoetsier.uva.dbbench.config;

import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {

  private static Logger logger = LoggerFactory.getLogger(ConnectorConfig.class);

  @Autowired
  private DbConfigProperties dbConfigProperties;

  @Bean
  public DatabaseConnector databaseConnector()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {

    logger.debug("Linking DatabaseConnector {}", dbConfigProperties.getConnector());

    return (DatabaseConnector) Class.forName(dbConfigProperties.getConnector()).newInstance();
  }
}
