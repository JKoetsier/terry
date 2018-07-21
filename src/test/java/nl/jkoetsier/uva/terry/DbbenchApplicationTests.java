package nl.jkoetsier.uva.terry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import nl.jkoetsier.uva.terry.config.DbConfigProperties;
import nl.jkoetsier.uva.terry.input.SchemaReader;
import nl.jkoetsier.uva.terry.input.WorkloadReader;
import nl.jkoetsier.uva.terry.intrep.schema.Schema;
import nl.jkoetsier.uva.terry.intrep.workload.Workload;
import nl.jkoetsier.uva.terry.testclass.IntegrationTest;
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
@Ignore
public class DbbenchApplicationTests {

  static {
    System.setProperty("outputdb", "testdb");
  }

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

    assertEquals("testUser", dbConfigProperties.getUsername());
  }
}
