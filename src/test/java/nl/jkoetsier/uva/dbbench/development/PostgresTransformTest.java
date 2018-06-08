package nl.jkoetsier.uva.dbbench.development;

import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.postgres.workload.PostgresWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.testclass.DevelopmentTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresTransformTest extends BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(PostgresTransformTest.class);

  protected SqlWorkloadVisitor getWorkloadVisitor() {
    return new PostgresWorkloadVisitor();
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
