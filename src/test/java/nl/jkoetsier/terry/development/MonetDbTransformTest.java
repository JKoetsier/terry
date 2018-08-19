package nl.jkoetsier.terry.development;

import nl.jkoetsier.terry.connector.SqlWorkloadVisitor;
import nl.jkoetsier.terry.connector.monetdb.workload.MonetDbWorkloadVisitor;
import nl.jkoetsier.terry.testclass.DevelopmentTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbTransformTest extends BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MonetDbTransformTest.class);

  protected SqlWorkloadVisitor getWorkloadVisitor() {
    return new MonetDbWorkloadVisitor();
  }

  @Override
  protected String stripQueryExtra(String query) {
    return query.replaceAll("(?i)isnull", "coalesce");
  }

  @Test
  @Override
  @Category(DevelopmentTest.class)
  public void testWorkload() {
    super.testWorkloadImpl();
  }
}
