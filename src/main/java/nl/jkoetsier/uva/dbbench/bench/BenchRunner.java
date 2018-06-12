package nl.jkoetsier.uva.dbbench.bench;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import nl.jkoetsier.uva.dbbench.bench.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.bench.monitoring.MonitoringThread;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.QueryResultRow;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
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
  }

  public void setup() throws DatabaseException {
    try {
      databaseInterface.connect();

      if (schema != null && importDataModel) {
        logger.info("Importing Schema");
        databaseInterface.importSchema(schema);
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

    waitMilliseconds(1000);

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (Entry<String, String> entry : queries.entrySet()) {
        try {

          long time = timeQuery(entry.getValue());

          if (i < skipFirst) {
            continue;
          }

          results.get(entry.getKey())[i - skipFirst] = nanoToMicro(time);

          logger.debug("Query {} Execution time: {}", entry.getKey(), formatTimeMicro(time));

          logger.debug("Results ({}):", lastResult.size());

          for (QueryResultRow resultRow : lastResult.getRows()) {
            logger.debug("{}", resultRow);
          }

          Query query = workload.getQuery(entry.getKey());
          validateQueryResult(lastResult, query.getExpectedResult());

        } catch (SQLException e) {
          throw new DatabaseException(e);
        }
      }
    }

    waitMilliseconds(3000);

    monitoringThread.end();


    if (applicationConfigProperties.getOutputDirectory() != null) {
      OutputWriter outputWriter = new OutputWriter(
          applicationConfigProperties.getOutputDirectory(),
          databaseInterface.getSimpleName()
      );

      SystemStatsCollection stats = monitoringThread.getSystemStatsCollection();

      try {
        outputWriter.writeResults(queries, results);
        outputWriter.writeSystemStats(stats);
      } catch (IOException e) {
        logger.error("Error occurred while trying to write results to file: {}", e.getMessage());
      }
    }

    tearDown();
  }

  private void validateQueryResult(QueryResult lastResult, QueryResult expectedResult) {
    // do something here
    if (lastResult.equals(expectedResult)) {
      logger.debug("Valid query result");
    } else {
      logger.error("Query result does not match expected result:");
      logger.error("Actual result: {}", lastResult);
      logger.error("Expected result: {}", expectedResult);
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
   * Returns time in nanoseconds
   */
  private long timeQuery(String query) throws SQLException {
    long start = System.nanoTime();

    databaseInterface.executeQuery(query);

    long end = System.nanoTime();

    lastResult = databaseInterface.getLastResults();

    return end - start;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public void setImportDataModel(boolean importDataModel) {
    this.importDataModel = importDataModel;
  }
}
