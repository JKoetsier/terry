package nl.jkoetsier.uva.dbbench.bench;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.LongStream;
import nl.jkoetsier.uva.dbbench.config.GlobalConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.DatabaseInterface;
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
    for (Entry<Integer, long[]> entry : results.entrySet()) {
      long avg = LongStream.of(entry.getValue()).sum() / entry.getValue().length;

      String values = "";
      for (long time : entry.getValue()) {
        values = values.concat(String.format("%6s ", time));
      }

      String row = String.format("%3s | avg: %6s\u00B5s | all: %s", entry.getKey(), avg, values);

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

    printSystemInfo();
    for (Entry<Integer, String> entry : queries.entrySet()) {
      results.put(entry.getKey(), new long[noRuns]);
    }

    for (int i = 0; i < noRuns + skipFirst; i++) {
      for (Entry<Integer, String> entry: queries.entrySet()) {
        long time = timeQuery(entry.getValue());

        if (i < skipFirst) {
          continue;
        }

        results.get(entry.getKey())[i - skipFirst] = time / 1000;

        logger.debug("Query {} Execution time: {}\u00B5s", entry.getKey(), time / 1000);
      }
    }

    printResults(results);

    tearDown();
  }

  private void printSystemInfo() {
    System.out.println("Available processors (cores): " +
        Runtime.getRuntime().availableProcessors());

    /* Total amount of free memory available to the JVM */
    System.out.println("Free memory (bytes): " +
        Runtime.getRuntime().freeMemory());

    /* This will return Long.MAX_VALUE if there is no preset limit */
    long maxMemory = Runtime.getRuntime().maxMemory();
    /* Maximum amount of memory the JVM will attempt to use */
    System.out.println("Maximum memory (bytes): " +
        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

    /* Total memory currently available to the JVM */
    System.out.println("Total memory available to JVM (bytes): " +
        Runtime.getRuntime().totalMemory());

    /* Get a list of all filesystem roots on this system */
    File[] roots = File.listRoots();

    /* For each filesystem root, print some info */
    for (File root : roots) {
      System.out.println("File system root: " + root.getAbsolutePath());
      System.out.println("Total space (bytes): " + root.getTotalSpace());
      System.out.println("Free space (bytes): " + root.getFreeSpace());
      System.out.println("Usable space (bytes): " + root.getUsableSpace());
    }
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
