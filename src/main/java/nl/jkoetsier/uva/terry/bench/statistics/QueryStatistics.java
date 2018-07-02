package nl.jkoetsier.uva.terry.bench.statistics;

import java.util.HashMap;
import java.util.Map.Entry;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.schema.Table;
import nl.jkoetsier.uva.terry.internal.workload.Query;
import nl.jkoetsier.uva.terry.internal.workload.meta.TableCounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryStatistics {

  private static Logger logger = LoggerFactory.getLogger(QueryStatistics.class);

  private Query query;

  private TableCounts touchedTables;
  private Integer sumTablesTouched;
  private Integer sumTableWidth;
  private Integer sumTableWidthWeighted;
  private HashMap<String, Integer> tableWidths;
  private HashMap<String, Long> tableLengths;
  private HashMap<String, Integer> tablesTouched;
  private Schema schema;

  public QueryStatistics(Query query, Schema schema) {
    this.query = query;
    this.schema = schema;
    gatherStatistics();
  }

  private void gatherStatistics() {
    StatisticsVisitor statisticsVisitor = new StatisticsVisitor(schema);
    query.acceptVisitor(statisticsVisitor);

    touchedTables = statisticsVisitor.getTouchedTables();

    sumTablesTouched = 0;
    sumTableWidth = 0;
    sumTableWidthWeighted = 0;
    tableWidths = new HashMap<>();
    tableLengths = new HashMap<>();
    tablesTouched = new HashMap<>();

    for (Entry<Table, Integer> touchedTableEntry : touchedTables.getAsMap().entrySet()) {
      Table table = touchedTableEntry.getKey();
      Integer cntTouched = touchedTableEntry.getValue();

      sumTablesTouched += cntTouched;
      sumTableWidth += table.getColumnCnt();
      sumTableWidthWeighted += cntTouched * table.getColumnCnt();
      tableWidths.put(table.getName(), table.getColumnCnt());
      tablesTouched.put(table.getName(), cntTouched);
      tableLengths.put(table.getName(), table.getRowCnt());
    }
  }

  public Query getQuery() {
    return query;
  }

  /**
   * @return Total number of tables touched
   */
  public Integer getSumTablesTouched() {
    return sumTablesTouched;
  }

  /**
   * @return Total width (column count) of all touched tables
   */
  public Integer getSumTableWidth() {
    return sumTableWidth;
  }

  /**
   * Weights table widths with the number of times a table is touched. A table with a
   * width of 20 that is touched 2 times is counted as 40 (2x20)
   *
   * @return Total widths of all touched tables, weighted
   */
  public Integer getSumTableWidthWeighted() {
    return sumTableWidthWeighted;
  }

  /**
   * @return Map of table names with corresponding widths
   */
  public HashMap<String, Integer> getTableWidths() {
    return tableWidths;
  }

  /**
   * @return Map of table names with corresponding length (number of rows)
   */
  public HashMap<String, Long> getTableLengths() {
    return tableLengths;
  }

  /**
   * @return Map of table names with number of times touched
   */
  public HashMap<String, Integer> getTablesTouched() {
    return tablesTouched;
  }
}
