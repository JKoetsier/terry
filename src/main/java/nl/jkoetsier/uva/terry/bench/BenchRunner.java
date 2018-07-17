package nl.jkoetsier.uva.terry.bench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.terry.bench.monitoring.MonitoringThread;
import nl.jkoetsier.uva.terry.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.terry.bench.results.Results;
import nl.jkoetsier.uva.terry.bench.results.ResultsWriter;
import nl.jkoetsier.uva.terry.bench.results.RunResult;
import nl.jkoetsier.uva.terry.bench.util.TimeConverter;
import nl.jkoetsier.uva.terry.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.terry.connector.DatabaseConnector;
import nl.jkoetsier.uva.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.terry.internal.ExecutableQuery;
import nl.jkoetsier.uva.terry.internal.QueryResult;
import nl.jkoetsier.uva.terry.internal.QueryResultRow;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchRunner {

  private static Logger logger = LoggerFactory.getLogger(BenchRunner.class);

  private DatabaseConnector databaseConnector;
  private ApplicationConfigProperties applicationConfigProperties;
  private Schema schema;
  private Workload workload;
  private QueryResult lastResult;

  public BenchRunner(DatabaseConnector databaseConnector,
      ApplicationConfigProperties applicationConfigProperties,
      Schema schema,
      Workload workload) {
    this.databaseConnector = databaseConnector;
    this.applicationConfigProperties = applicationConfigProperties;
    this.schema = schema;
    this.workload = workload;
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

    List<ExecutableQuery> queries = new ArrayList<>(databaseConnector.getWorkloadQueries(workload));

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
            TimeConverter.formatTimeNanoToMicro(result.getResponseTimeNs()),
            TimeConverter.formatTimeNanoToMicro(result.getResultsTimeNs()));

        if (!applicationConfigProperties.skipResultValidation() && query.getQueryObject().getExpectedResult() != null) {
          validateQueryResult(lastResult, query.getQueryObject().getExpectedResult());
        }

      }
    }

    waitMilliseconds(3000);

    monitoringThread.end();

    if (applicationConfigProperties.getOutputDirectory() != null) {
      ResultsWriter resultsWriter = new ResultsWriter(
          applicationConfigProperties.getOutputDirectory(),
          databaseConnector.getSimpleName(),
          schema
      );

      SystemStatsCollection stats = monitoringThread.getSystemStatsCollection();

      try {
        resultsWriter.writeResults(results);
        resultsWriter.writeSystemStats(stats);
      } catch (IOException e) {
        logger.error("Error occurred while trying to write results to file: {}", e.getMessage());
      }
    }

    databaseConnector.closeConnection();
  }

  private void validateQueryResult(QueryResult lastResult, QueryResult expectedResult) {
    lastResult.order();
    databaseConnector.translateQueryResults(lastResult, expectedResult);
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



  /**
   * Returns execution time of query in nanoseconds.
   */
  private RunResult timeQuery(ExecutableQuery query) throws DatabaseException {
    RunResult result = new RunResult();

    long start = System.nanoTime();

    databaseConnector.executeQuery(query);

    result.setResponseTimeNs(System.nanoTime() - start);

    lastResult = databaseConnector.getLastResults();
    result.setResultsTimeNs(System.nanoTime() - start);

    return result;
  }
}
