package nl.jkoetsier.uva.dbbench;

import static org.junit.Assert.*;

import nl.jkoetsier.uva.dbbench.config.CommandLineConfigProperties;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;
import nl.jkoetsier.uva.dbbench.input.WorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.testclass.IntegrationTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Category(IntegrationTest.class)
public class DbbenchApplicationTests {

  static {
    System.setProperty("outputdb", "testdb");
  }

  @Autowired
  private CommandLineConfigProperties commandLineConfigProperties;

  @Autowired
  private DbConfigProperties dbConfigProperties;

  @Autowired
  private WorkloadReader workloadReader;

  @Autowired
  private SchemaReader schemaReader;

  @Test
  public void configPropertiesLoad() {
    Mockito.when(workloadReader.fromFile("workload.sql")).thenReturn(new Workload());
    Mockito.when(schemaReader.fromFile("datamodel.sql")).thenReturn(new Schema());
    assertNotNull(dbConfigProperties);
    assertNotNull(commandLineConfigProperties);

    assertEquals("testUser", dbConfigProperties.getUsername());
  }

}
