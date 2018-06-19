package nl.jkoetsier.uva.dbbench.bench;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import nl.jkoetsier.uva.dbbench.bench.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.bench.monitoring.MonitoringThread;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.dbbench.bench.querystripper.QueryStripper;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.QueryResultRow;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchRunner {

  private static Logger logger = LoggerFactory.getLogger(BenchRunner.class);

  private DatabaseConnector databaseInterface;
  private Schema schema;
  private Workload workload;
  private ApplicationConfigProperties applicationConfigProperties;
  private String dataDirectory;
  private boolean importDataModel;
  private QueryResult lastResult;

  public BenchRunner(DatabaseConnector databaseInterface,
      ApplicationConfigProperties applicationConfigProperties) {
    this.databaseInterface = databaseInterface;
    this.applicationConfigProperties = applicationConfigProperties;
  }

  public void setSchema(Schema schema) {
    this.schema = schema;
  }

  public void setWorkload(Workload workload) {
    this.workload = workload;

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

  public void setup() throws DatabaseException {
    try {
      databaseInterface.connect();

      if (schema != null && importDataModel) {
        logger.info("Importing Schema");
        databaseInterface.importSchema(schema);
      }

      if (schema != null) {
        readTableSizes();
      }

      if (dataDirectory != null) {
        logger.info("Importing CSV Data");
        long start = System.nanoTime();

        databaseInterface.importCsvData(dataDirectory);

        long end = System.nanoTime();

        logger.info("Importing CSV took {} seconds", nanoToSeconds(end - start));
      }
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  private void readTableSizes() throws SQLException {
    for (Table table : schema.getTables().values()) {
      int tableSize = databaseInterface.getTableSize(table.getName());
      table.setRowCnt(tableSize);

      logger.debug("Table {} size: {}", table.getName(), tableSize);
    }
  }


  private void tearDown() {
    databaseInterface.closeConnection();
  }

  private void printQueries(TreeMap<String, String> queries) {
    for (Entry<String, String> entry : queries.entrySet()) {
      logger.info("{}: {}", entry.getKey(), entry.getValue());
    }
  }

  public void run() throws DatabaseException {
    logger.info("Start running bench");

    int noRuns = applicationConfigProperties.getNoRuns();
    int skipFirst = applicationConfigProperties.getSkipFirst();

    TreeMap<String, String> queries = new TreeMap<>(
        databaseInterface.getWorkloadQueries(workload));
    HashMap<String, long[]> results = new HashMap<>();

    printQueries(queries);

    for (Entry<String, String> entry : queries.entrySet()) {
      results.put(entry.getKey(), new long[noRuns]);
    }

    MonitoringThread monitoringThread = new MonitoringThread();
    monitoringThread.start();

    waitMilliseconds(3000);

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (Entry<String, String> entry : queries.entrySet()) {
        try {
          logger.debug("Running query {}", entry.getKey());

          long time;
          try {
            time = timeQuery(entry.getValue());


            // TODO Tmp dirty hack for failing subqueries (that don't have all necessary fields included etc)
          } catch (SQLException e) {
            logger.debug("Caught Exception");
            logger.debug("Entry key: " + entry.getKey());
            logger.debug("Have dash: " + entry.getKey().contains("-"));

            if (entry.getKey().contains("-")) {
              time = 0;
            } else {
              throw e;
            }
          }

          if (i < skipFirst) {
            continue;
          }

          results.get(entry.getKey())[i - skipFirst] = nanoToMicro(time);

          logger.debug("Query {} Execution time: {}", entry.getKey(), formatTimeMicro(time));

          if (time != 0) {
            lastResult = databaseInterface.getLastResults();
            logger.debug("Have {} results", lastResult.size());
          }

          Query query = workload.getQuery(entry.getKey());

          if (query.getExpectedResult() != null) {
            validateQueryResult(lastResult, query.getExpectedResult());
          }

        } catch (SQLException e) {
          throw new DatabaseException(e);
        }
      }
    }

    waitMilliseconds(3000);

    monitoringThread.end();

    if (applicationConfigProperties.getOutputDirectory() != null) {
      ResultsWriter resultsWriter = new ResultsWriter(
          applicationConfigProperties.getOutputDirectory(),
          databaseInterface.getSimpleName()
      );

      SystemStatsCollection stats = monitoringThread.getSystemStatsCollection();

      try {
        resultsWriter.writeResults(workload, schema, queries, results);
        resultsWriter.writeSystemStats(stats);
      } catch (IOException e) {
        logger.error("Error occurred while trying to write results to file: {}", e.getMessage());
      }
    }

    tearDown();
  }

  private void validateQueryResult(QueryResult lastResult, QueryResult expectedResult) {
    lastResult.order();
    databaseInterface.translateQueryResults(lastResult, expectedResult);
    expectedResult.order();

    // do something here
    if (lastResult.equals(expectedResult)) {
      logger.debug("Valid query result");
    } else {
      logger.error("Query result does not match expected result:");

      if (lastResult.size() != expectedResult.size()) {
        logger.error("Sizes do not match. Have {} results, but expected {}", lastResult.size(),
            expectedResult.size());

        if (expectedResult.size() > lastResult.size()) {
          for (QueryResultRow row : expectedResult.getRows()) {
            if (!lastResult.getRows().contains(row)) {
              logger.error("Missing row in expected results: {}", row);
            }
          }
        } else {
          for (QueryResultRow row : lastResult.getRows()) {
            if (!expectedResult.getRows().contains(row)) {
              logger.error("Did not expect to see this row in results: {}", row);
            }
          }
        }
      }

      for (int i = 0; i < lastResult.size(); i++) {
        if (!lastResult.getRows().get(i).equals(expectedResult.getRows().get(i))) {
          logger.error("Row {} does not match:", i);
          logger.error("Actual    ({}): {}", lastResult.getRows().get(i).size(),
              lastResult.getRows().get(i));
          logger.error("Expected: ({}): {}", expectedResult.getRows().get(i).size(),
              expectedResult.getRows().get(i));
        }
      }
    }
  }


  private void waitMilliseconds(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      logger.debug("BenchRunner interrupted");
    }
  }


  private long nanoToMicro(long nano) {
    return nano / 1000;
  }

  private long nanoToSeconds(long nano) {
    return nanoToMicro(nano) / 1000;
  }

  private String formatTimeMicro(long nanoTime) {
    return String.format("%d\u00B5s", nanoToMicro(nanoTime));
  }

  /**
   * Returns execution time of query in nanoseconds.
   */
  private long timeQuery(String query) throws SQLException {
    long start = System.nanoTime();

    databaseInterface.executeQuery(query);

    long end = System.nanoTime();

    return end - start;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public void setImportDataModel(boolean importDataModel) {
    this.importDataModel = importDataModel;
  }
}
