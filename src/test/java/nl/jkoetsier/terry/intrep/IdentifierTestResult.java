package nl.jkoetsier.terry.intrep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierTestResult {

  private static Logger logger = LoggerFactory.getLogger(IdentifierTestResult.class);

  private String idA;
  private String idB;
  private int result;

  public IdentifierTestResult(String idA, String idB, int result) {
    this.idA = idA;
    this.idB = idB;
    this.result = result;
  }

  public String getIdA() {
    return idA;
  }

  public String getIdB() {
    return idB;
  }

  public int getResult() {
    return result;
  }
}
