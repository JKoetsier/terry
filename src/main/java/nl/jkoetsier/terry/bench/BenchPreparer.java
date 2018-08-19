package nl.jkoetsier.terry.bench;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.terry.bench.querystripper.QueryStripper;
import nl.jkoetsier.terry.connector.DatabaseConnector;
import nl.jkoetsier.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.schema.Table;
import nl.jkoetsier.terry.intrep.workload.Query;
import nl.jkoetsier.terry.intrep.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchPreparer {

  private static Logger logger = LoggerFactory.getLogger(BenchPreparer.class);

  private DatabaseConnector databaseConnector;
  private Schema schema;
  private Workload workload;

  public BenchPreparer(DatabaseConnector databaseConnector,
      Schema schema, Workload workload) {
    this.databaseConnector = databaseConnector;
    this.schema = schema;
    this.workload = workload;
  }

  public void prepare() throws DatabaseException {
    readTableSizes();

    extractSubQueries();
  }

  private void extractSubQueries() {
    List<Query> subQueries = new ArrayList<>();

    for (Query query : workload.getQueries().values()) {
      List<Query> stripped = QueryStripper.stripQuery(query);

      for (int i = 0; i < stripped.size(); i++) {
        Query subQuery = stripped.get(i);
        subQuery.setIdentifier(query.getIdentifier() + "-" + i);
        subQueries.add(subQuery);
      }
    }

    for (Query subQuery : subQueries) {
      workload.addQuery(subQuery);
    }
  }

  private void readTableSizes() throws DatabaseException {
    for (Table table : schema.getTables().values()) {
      long tableSize = databaseConnector.getTableSize(table.getName());
      table.setRowCnt(tableSize);

      logger.debug("Table {} size: {}", table.getName(), tableSize);
    }
  }
}
