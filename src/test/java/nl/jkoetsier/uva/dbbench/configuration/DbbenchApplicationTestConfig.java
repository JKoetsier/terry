package nl.jkoetsier.uva.dbbench.configuration;

import nl.jkoetsier.uva.dbbench.config.CommandLineConfigProperties;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;
import nl.jkoetsier.uva.dbbench.input.WorkloadReader;
import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class DbbenchApplicationTestConfig {

  private static Logger logger = LoggerFactory.getLogger(DbbenchApplicationTestConfig.class);

  @Bean
  @Primary
  public CommandLineConfigProperties commandLineConfigProperties() {
    CommandLineConfigProperties commandLineConfigProperties = new CommandLineConfigProperties();
    commandLineConfigProperties.setDatamodel("datamodel.sql");
    commandLineConfigProperties.setOutputDb("outputdb");
    commandLineConfigProperties.setWorkload("workload.sql");

    return commandLineConfigProperties;
  }

  @Bean
  @Primary
  public WorkloadReader workloadReader() {
    return Mockito.mock(SqlWorkloadReader.class);
  }

  @Bean
  @Primary
  public SchemaReader schemaReader() {
    return Mockito.mock(SqlSchemaReader.class);
  }
}
