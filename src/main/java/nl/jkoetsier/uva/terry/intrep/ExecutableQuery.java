package nl.jkoetsier.uva.terry.intrep;

import nl.jkoetsier.uva.terry.intrep.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExecutableQuery implements Comparable<ExecutableQuery> {

  private static Logger logger = LoggerFactory.getLogger(ExecutableQuery.class);

  private boolean supported = true;
  private Query queryObject;

  public ExecutableQuery() {

  }

  public ExecutableQuery(Query queryObject) {
    this.queryObject = queryObject;
  }

  public String getIdentifier() {
    return queryObject.getIdentifier();
  }

  public boolean isSupported() {
    return supported;
  }

  public void setSupported(boolean supported) {
    this.supported = supported;
  }

  public Query getQueryObject() {
    return queryObject;
  }

  public void setQueryObject(Query queryObject) {
    this.queryObject = queryObject;
  }

  @Override
  public int compareTo(ExecutableQuery o) {
    return compareIdentifiers(getIdentifier(), o.getIdentifier());
  }

  int compareIdentifiers(String identifierA, String identifierB) {
    if (isNumericId(identifierA) && isNumericId(identifierB)) {
      return compareNumericIdentifiers(identifierA, identifierB);

    } else {
      return identifierA.compareTo(identifierB);
    }
  }

  private int compareNumericIdentifiers(String identifierA, String identifierB) {
    Integer[] idAparts = getIdentifierAsIntegers(identifierA);
    Integer[] idBparts = getIdentifierAsIntegers(identifierB);

    if (idAparts[0] < idBparts[0]) {
      return -1;
    }

    if (idAparts[0] > idBparts[0]) {
      return 1;
    }

    if (idAparts.length < 2 && idBparts.length < 2) {
      return 0;
    }

    if (idAparts.length < 2) {
      return -1;
    }

    if (idBparts.length < 2) {
      return 1;
    }

    if (idAparts[1] < idBparts[1]) {
      return -1;
    }

    if (idAparts[1] > idBparts[1]) {
      return 1;
    }

    return 0;
  }

  private Integer[] getIdentifierAsIntegers(String identifier) {
    String[] parts = identifier.split("-");
    Integer[] intParts = new Integer[parts.length];

    for (int i = 0; i < parts.length; i++) {
      intParts[i] = Integer.parseInt(parts[i]);
    }

    return intParts;
  }

  private boolean isNumericId(String identifier) {
    String[] splittedIdentifier = identifier.split("-");

    for (String part : splittedIdentifier) {
      if (!isInteger(part)) {
        return false;
      }
    }

    return true;
  }

  private boolean isInteger(String strNum) {
    try {
      double d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }
}
