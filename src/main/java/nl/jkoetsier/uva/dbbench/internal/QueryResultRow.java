package nl.jkoetsier.uva.dbbench.internal;

import java.util.Arrays;
import nl.jkoetsier.uva.dbbench.connector.ValueTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryResultRow implements Comparable<QueryResultRow> {

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

  @Override
  public int compareTo(QueryResultRow o) {
    int currentItem;
    for (int i = 0, j = 0; i < values.length && j < o.values.length; i++, j++) {

      if ((currentItem = values[i].compareTo(o.values[j])) != 0) {
        return currentItem;
      }
    }

    return 0;
  }

  public int size() {
    return values.length;
  }

  public void replaceValues(String value, String with) {
    for (int i = 0; i < values.length; i++) {
      if (values[i].equals(value)) {
        values[i] = with;
      }
    }
  }

  public void replaceValues(ValueTranslator translator) {
    for (int i = 0; i < values.length; i++) {
      values[i] = translator.translate(values[i]);
    }
  }
}
