package nl.jkoetsier.uva.dbbench.bench;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.LongStream;
import nl.jkoetsier.uva.dbbench.config.GlobalConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.connector.DatabaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchRunner {

  private static Logger logger = LoggerFactory.getLogger(BenchRunner.class);

  private DatabaseInterface databaseInterface;
  private Schema schema;
  private Workload workload;
  private GlobalConfigProperties globalConfigProperties;

  public BenchRunner(DatabaseInterface databaseInterface, GlobalConfigProperties globalConfigProperties) {
    this.databaseInterface = databaseInterface;
    this.globalConfigProperties = globalConfigProperties;
  }

  public void setSchema(Schema schema) {
    this.schema = schema;
  }

  public void setWorkload(Workload workload) {
    this.workload = workload;
  }

  private void setup() {
    databaseInterface.connect();

    if (schema != null) {
      databaseInterface.importSchema(schema);
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

      String row = String.format("%3s | %7s | %s", entry.getKey(), String.format("%s\u00B5s", avg), values);

      logger.info(row);
    }
  }

  public void run() {
    setup();

    int noRuns = globalConfigProperties.getNoRuns();
    int skipFirst = globalConfigProperties.getSkipFirst();

    TreeMap<Integer, String> queries = new TreeMap<>(databaseInterface.getWorkloadQueries(workload));
    HashMap<Integer, long[]> results = new HashMap<>();

    printQueries(queries);

    for (Entry<Integer, String> entry : queries.entrySet()) {
      results.put(entry.getKey(), new long[noRuns]);
    }

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (Entry<Integer, String> entry: queries.entrySet()) {
        long time = timeQuery(entry.getValue());

        if (i < skipFirst) {
          continue;
        }

        results.get(entry.getKey())[i - skipFirst] = nanoToMicro(time);

        logger.debug("Query {} Execution time: {}", entry.getKey(), formatTimeMicro(time));
      }
    }

    printResults(results);

    tearDown();
  }

  private long nanoToMicro(long nano) {
    return nano / 1000;
  }

  private String formatTimeMicro(long nanoTime) {
    return String.format("%d\u00B5s", nanoToMicro(nanoTime));
  }

  /**
   * Returns time in nanoseconds
   * @param query
   * @return
   */
  private long timeQuery(String query) {
    long start = System.nanoTime();

    databaseInterface.executeQuery(query);

    long end = System.nanoTime();

    return end - start;
  }
}
