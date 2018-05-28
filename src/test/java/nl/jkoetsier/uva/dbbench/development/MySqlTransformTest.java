package nl.jkoetsier.uva.dbbench.development;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.mysql.workload.MySqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.testclass.DevelopmentTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlTransformTest extends BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MySqlTransformTest.class);

  protected SqlWorkloadVisitor getWorkloadVisitor() {
    return new MySqlWorkloadVisitor();
  }

  @Test
  @Override
  @Category(DevelopmentTest.class)
  public void testWorkload() {
    super.testWorkloadImpl();
  }
}
