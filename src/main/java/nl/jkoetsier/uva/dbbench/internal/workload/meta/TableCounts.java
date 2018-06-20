package nl.jkoetsier.uva.dbbench.internal.workload.meta;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableCounts {

  private static Logger logger = LoggerFactory.getLogger(TableCounts.class);

  private HashMap<Table, Integer> touchedTables;

  public TableCounts() {
    this.touchedTables = new HashMap<>();
  }

  public TableCounts(Table table, Integer count) {
    this();
    add(table, count);
  }

  public static TableCounts add(TableCounts... touchedTables) {
    TableCounts result = new TableCounts();

    for (TableCounts tt : touchedTables) {
      result.addAllFrom(tt);
    }

    return result;
  }

  public void add(Table table, Integer count) {
    Integer existingCnt = get(table);

    if (existingCnt != null) {
      count += existingCnt;
    }

    touchedTables.put(table, count);
  }

  public Integer get(Table table) {
    return touchedTables.get(table);
  }

  public HashMap<Table, Integer> getAsMap() {
    return touchedTables;
  }

  public void add(TableCounts tableCounts) {
    addAllFrom(tableCounts);
  }

  private void addAllFrom(TableCounts fromObj) {
    for (Entry<Table, Integer> entry : fromObj.getAsMap().entrySet()) {
      add(entry.getKey(), entry.getValue());
    }
  }
}
