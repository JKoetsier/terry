package nl.jkoetsier.uva.dbbench.bench;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.LongStream;
import nl.jkoetsier.uva.dbbench.bench.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.bench.monitoring.MonitoringThread;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
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

  private void printQueries(TreeMap<Integer, String> queries) {
    for (Entry<Integer, String> entry : queries.entrySet()) {
      logger.info("{}: {}", entry.getKey(), entry.getValue());
    }
  }

  private void printResults(HashMap<Integer, long[]> results) {
    long[] firstRow = results.get(0);
    String runs = String.format("%6s", "all");

    if (firstRow != null) {
      for (int i = 1; i < firstRow.length; i++) {
        runs = runs.concat(String.format("%6s", i));
      }
    }

    String header = String.format("%3s | %7s | %s", "Q", "avg", runs);
    logger.info(header);

    for (Entry<Integer, long[]> entry : results.entrySet()) {
      long avg = LongStream.of(entry.getValue()).sum() / entry.getValue().length;

      String values = "";
      for (long time : entry.getValue()) {
        values = values.concat(String.format("%6s", time));
      }

      String row = String
          .format("%3s | %7s | %s", entry.getKey(), String.format("%s\u00B5s", avg), values);

      logger.info(row);
    }
  }

  public void run() throws DatabaseException {
    logger.info("Start running bench");

    int noRuns = applicationConfigProperties.getNoRuns();
    int skipFirst = applicationConfigProperties.getSkipFirst();

    TreeMap<Integer, String> queries = new TreeMap<>(
        databaseInterface.getWorkloadQueries(workload));
    HashMap<Integer, long[]> results = new HashMap<>();

    printQueries(queries);

    for (Entry<Integer, String> entry : queries.entrySet()) {
      results.put(entry.getKey(), new long[noRuns]);
    }

    MonitoringThread monitoringThread = new MonitoringThread();
    monitoringThread.start();

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (Entry<Integer, String> entry : queries.entrySet()) {
        try {

          long time = timeQuery(entry.getValue());

          if (i < skipFirst) {
            continue;
          }

          results.get(entry.getKey())[i - skipFirst] = nanoToMicro(time);

          logger.debug("Query {} Execution time: {}", entry.getKey(), formatTimeMicro(time));

        } catch (SQLException e) {
          throw new DatabaseException(e);
        }
      }
    }
    try {
      monitoringThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    printResults(results);

    tearDown();
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

    return end - start;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public void setImportDataModel(boolean importDataModel) {
    this.importDataModel = importDataModel;
  }
}
