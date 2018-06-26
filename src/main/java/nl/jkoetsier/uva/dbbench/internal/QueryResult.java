package nl.jkoetsier.uva.dbbench.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import nl.jkoetsier.uva.dbbench.connector.ValueTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryResult {

  private static Logger logger = LoggerFactory.getLogger(QueryResult.class);

  private List<QueryResultRow> rows;

  public QueryResult() {
    rows = new ArrayList<>();
  }

  public List<QueryResultRow> getRows() {
    return rows;
  }

  public void setRows(List<QueryResultRow> rows) {
    this.rows = rows;
  }

  public int size() {
    return rows.size();
  }

  public int rowWidth() {
    if (size() == 0) {
      return 0;
    }

    return rows.get(0).size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResult that = (QueryResult) o;
    return Objects.equals(rows, that.rows);
  }

  @Override
  public int hashCode() {

    return Objects.hash(rows);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("QueryResult:\n");

    for (QueryResultRow row : rows) {
      stringBuilder.append(row.toString());
      stringBuilder.append("\n");
    }

    return stringBuilder.toString();
  }

  public void order() {
    Collections.sort(rows);
  }

  public void replaceValues(String value, String with) {
    for (QueryResultRow row : rows) {
      row.replaceValues(value, with);
    }
  }

  public void replaceValues(ValueTranslator translator) {
    for (QueryResultRow row : rows) {
      row.replaceValues(translator);
    }
  }
}
