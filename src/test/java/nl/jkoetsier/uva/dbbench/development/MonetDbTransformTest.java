package nl.jkoetsier.uva.dbbench.development;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.monetdb.workload.MonetDbWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.testclass.DevelopmentTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbTransformTest extends BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MonetDbTransformTest.class);

  protected SqlWorkloadVisitor getWorkloadVisitor() {
    return new MonetDbWorkloadVisitor();
  }

  @Test
  @Override
  @Category(DevelopmentTest.class)
  public void testWorkload() {
    super.testWorkloadImpl();
  }
}
