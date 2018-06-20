package nl.jkoetsier.uva.dbbench.bench.statistics;

import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.meta.TableCounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryStatistics {

  private static Logger logger = LoggerFactory.getLogger(QueryStatistics.class);

  private Query query;

  private TableCounts touchedTables;

  public QueryStatistics(Query query) {
    this.query = query;
    gatherStatistics();
  }

  public TableCounts getTouchedTables() {
    return touchedTables;
  }

  private void gatherStatistics() {
    StatisticsVisitor statisticsVisitor = new StatisticsVisitor();
    query.acceptVisitor(statisticsVisitor);

    touchedTables = statisticsVisitor.getTouchedTables();
  }
}
