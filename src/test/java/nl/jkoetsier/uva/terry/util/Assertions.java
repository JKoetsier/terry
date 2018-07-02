package nl.jkoetsier.uva.terry.util;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assertions {

  private static Logger logger = LoggerFactory.getLogger(Assertions.class);


  /**
   * Not the perfect check at the moment. Identifiers are quoted throughout the application, which
   * means that they should always be case sensitive as some DBMS's treat quoted identifiers as
   * being case sensitive. For now we ignore this in the tests.
   */
  public static void assertIdentifierEquals(String expected, String real) {
    assertEquals(expected.toLowerCase(), real.toLowerCase());
  }

  /**
   * Not the perfect check at the moment. Identifiers should always be lowercase, which is not
   * checked by this method.
   */
  public static void assertQueryEquals(String expected, String real) {
    assertEquals(expected.toLowerCase(), real.toLowerCase());
  }
}
