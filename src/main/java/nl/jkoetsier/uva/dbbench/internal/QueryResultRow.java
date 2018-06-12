package nl.jkoetsier.uva.dbbench.internal;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryResultRow {

  private static Logger logger = LoggerFactory.getLogger(QueryResultRow.class);

  private String[] values;

  public QueryResultRow() {
  }

  public QueryResultRow(String[] values) {
    this.values = values;
  }

  public String[] getValues() {
    return values;
  }

  public void setValues(String[] values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return String.join(",", values);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResultRow resultRow = (QueryResultRow) o;
    return Arrays.equals(values, resultRow.values);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }
}
