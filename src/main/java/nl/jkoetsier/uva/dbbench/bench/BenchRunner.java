package nl.jkoetsier.uva.dbbench.bench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.bench.monitoring.MonitoringThread;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.dbbench.bench.querystripper.QueryStripper;
import nl.jkoetsier.uva.dbbench.bench.results.Results;
import nl.jkoetsier.uva.dbbench.bench.results.RunResult;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
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
  }

  private void readTableSizes() throws DatabaseException {
    for (Table table : schema.getTables().values()) {
      long tableSize = databaseInterface.getTableSize(table.getName());
      table.setRowCnt(tableSize);

      logger.debug("Table {} size: {}", table.getName(), tableSize);
    }
  }


  private void tearDown() {
    databaseInterface.closeConnection();
  }

  private void printQueries(List<ExecutableQuery> queries) {
    for (ExecutableQuery query : queries) {
      logger.info("{}: {}", query.getIdentifier(), query.toString());
    }
  }

  public void run() throws DatabaseException {
    logger.info("Start running bench");

    int noRuns = applicationConfigProperties.getNoRuns();
    int skipFirst = applicationConfigProperties.getSkipFirst();

    List<ExecutableQuery> queries = new ArrayList<>(databaseInterface.getWorkloadQueries(workload));

    printQueries(queries);

    Results results = new Results(queries);

    MonitoringThread monitoringThread = new MonitoringThread();
    monitoringThread.start();

    waitMilliseconds(3000);

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (ExecutableQuery query : queries) {
        logger.debug("Running query {}", query.getIdentifier());

        RunResult result = new RunResult();
        if (query.isSupported()) {
          try {
            result = timeQuery(query);

            // TODO Tmp dirty hack for failing subqueries (that don't have all necessary fields included etc)
          } catch (DatabaseException e) {
            if (query.getIdentifier().contains("-")) {
              result.setValid(false);
            } else {
              throw e;
            }
          }
        } else {
          result.setSupported(false);
          result.setValid(false);
        }

        if (i < skipFirst) {
          continue;
        }

        result.setResultLength(lastResult.size());
        result.setResultWidth(lastResult.rowWidth());

        results.add(query, result);

        logger.debug("Query {} Execution time: {}/{}", query.getIdentifier(),
            formatTimeMicro(result.getResponseTimeNs()),
            formatTimeMicro(result.getResultsTimeNs()));

        if (query.getQueryObject().getExpectedResult() != null) {
          validateQueryResult(lastResult, query.getQueryObject().getExpectedResult());
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
        resultsWriter.writeResults(results);
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
  private RunResult timeQuery(ExecutableQuery query) throws DatabaseException {
    RunResult result = new RunResult();

    long start = System.nanoTime();

    databaseInterface.executeQuery(query);

    result.setResponseTimeNs(System.nanoTime() - start);

    lastResult = databaseInterface.getLastResults();
    result.setResultsTimeNs(System.nanoTime() - start);

    return result;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public void setImportDataModel(boolean importDataModel) {
    this.importDataModel = importDataModel;
  }
}
