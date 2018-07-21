package nl.jkoetsier.uva.terry.intrep;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutableQueryTest {

  private static Logger logger = LoggerFactory.getLogger(ExecutableQueryTest.class);

  private List<IdentifierTestResult> testResults = new ArrayList<>();
  {
    testResults.add(new IdentifierTestResult("a", "b", -1));
    testResults.add(new IdentifierTestResult("a", "a", 0));
    testResults.add(new IdentifierTestResult("b", "a", 1));
    testResults.add(new IdentifierTestResult("aa", "a", 1));

    testResults.add(new IdentifierTestResult("1", "2", -1));
    testResults.add(new IdentifierTestResult("2", "1", 1));
    testResults.add(new IdentifierTestResult("2", "2", 0));
    testResults.add(new IdentifierTestResult("22", "2", 1));
    testResults.add(new IdentifierTestResult("2", "2-0", -1));
    testResults.add(new IdentifierTestResult("2-0", "2", 1));
    testResults.add(new IdentifierTestResult("2-1", "2-0", 1));
    testResults.add(new IdentifierTestResult("1-1", "2-0", -1));
    testResults.add(new IdentifierTestResult("aa-0", "aa-1", -1));
    testResults.add(new IdentifierTestResult("aa-0", "aa", 2));
    testResults.add(new IdentifierTestResult("b-0", "a-1", 1));
  }

  @Test
  public void testCompareIdentifers() {
    ExecutableQueryTestChild executableQuery = new ExecutableQueryTestChild();

    for (IdentifierTestResult testResult : testResults) {
      assertEquals(String.format("'%s' and '%s' should yield '%d'",
          testResult.getIdA(), testResult.getIdB(), testResult.getResult()),
          testResult.getResult(), executableQuery.compareIdentifiers(
          testResult.getIdA(), testResult.getIdB()
      ));
    }

  }
}
