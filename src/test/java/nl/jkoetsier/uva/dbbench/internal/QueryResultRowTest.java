package nl.jkoetsier.uva.dbbench.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QueryResultRowTest {

  @Test
  public void compareTo() {
    QueryResultRow a = new QueryResultRow(new String[]{"abc", "bcd", "aef", "dgh"});
    QueryResultRow b = new QueryResultRow();

    b.setValues(new String[]{"bbc", "bcd", "aef", "dgh"});
    checkCompareTo(a, b, -1);

    b.setValues(new String[]{"aac", "bcd", "aef", "dgh"});
    checkCompareTo(a, b, 1);

    b.setValues(new String[]{"abc", "bcd", "adf", "dgh"});
    checkCompareTo(a, b, 1);

    b.setValues(new String[]{"abc", "bcd", "aff", "dgh"});
    checkCompareTo(a, b, -1);

    b.setValues(new String[]{"abc", "bcd", "aef", "dgh"});
    checkCompareTo(a, b, 0);

    b.setValues(new String[]{"abc", "bcd", "aef", "dgi"});
    checkCompareTo(a, b, -1);

    b.setValues(new String[]{"abc", "bcd", "aef", "dgg"});
    checkCompareTo(a, b, 1);
  }

  public void checkCompareTo(QueryResultRow a, QueryResultRow b, int expectedResult) {
    assertEquals(expectedResult, a.compareTo(b));
  }
}