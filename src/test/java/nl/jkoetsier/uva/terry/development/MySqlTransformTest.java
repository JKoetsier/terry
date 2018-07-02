package nl.jkoetsier.uva.terry.development;

import nl.jkoetsier.uva.terry.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.terry.connector.mysql.workload.MySqlWorkloadVisitor;
import nl.jkoetsier.uva.terry.testclass.DevelopmentTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlTransformTest extends BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MySqlTransformTest.class);

  protected SqlWorkloadVisitor getWorkloadVisitor() {
    return new MySqlWorkloadVisitor();
  }

  @Override
  protected String stripQueryExtra(String query) {
    return query.replaceAll("ifnull", "isnull");
  }

  @Test
  @Override
  @Category(DevelopmentTest.class)
  public void testWorkload() {
    super.testWorkloadImpl();
  }
}
