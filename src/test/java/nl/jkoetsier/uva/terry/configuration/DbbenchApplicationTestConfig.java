package nl.jkoetsier.uva.terry.configuration;

import nl.jkoetsier.uva.terry.input.SchemaReader;
import nl.jkoetsier.uva.terry.input.WorkloadReader;
import nl.jkoetsier.uva.terry.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.terry.input.workload.sql.SqlWorkloadReader;
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
  public WorkloadReader workloadReader() {
    return Mockito.mock(SqlWorkloadReader.class);
  }

  @Bean
  @Primary
  public SchemaReader schemaReader() {
    return Mockito.mock(SqlSchemaReader.class);
  }
}