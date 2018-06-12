package nl.jkoetsier.uva.dbbench.bench;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.LongStream;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollectionWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputWriter {

  private static Logger logger = LoggerFactory.getLogger(OutputWriter.class);

  private String outputDirectory;
  private String dbIdentifier;
  private String dateTimeStr;

  public OutputWriter(String outputDirectory, String dbIdentifier) {
    this.outputDirectory = outputDirectory;
    this.dbIdentifier = dbIdentifier;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    dateTimeStr = now.format(dateTimeFormatter);
  }

  private void createDirectory() throws IOException {
    Path directory = Paths.get(outputDirectory);

    if (!Files.exists(directory)) {
      Files.createDirectories(directory);

      logger.debug("Directory {} created", outputDirectory);
    } else {
      logger.debug("Directory {} already exists", outputDirectory);
    }
  }

  public void writeResults(Map<String, String> queries, Map<String, long[]> results)
      throws IOException {

    String outputCsvFilename = String.format("%s/%s_%s_timings.csv",
        outputDirectory,
        dateTimeStr,
        dbIdentifier
    );

    String outputQueriesFilename = String.format("%s/%s_%s_queries.txt",
        outputDirectory,
        dateTimeStr,
        dbIdentifier
    );

    createDirectory();

    writeResults(results, outputCsvFilename);
    writeQueries(queries, outputQueriesFilename);
  }

  public void writeSystemStats(SystemStatsCollection systemStatsCollection) throws IOException {
    SystemStatsCollectionWriter writer = new SystemStatsCollectionWriter();

    String outputFilename = String.format("%s/%s_%s_systemstats.csv",
        outputDirectory,
        dateTimeStr,
        dbIdentifier
    );

    writer.writeCsv(systemStatsCollection, outputFilename);
  }

  private void writeResults(Map<String, long[]> results, String filePath) throws IOException {
    if (results.size() == 0) {
      return;
    }

    List<String> keys = new ArrayList<>(results.keySet());
    long[] firstRow = results.get(keys.get(0));

    String[] headers = new String[firstRow.length + 1];
    headers[0] = "query";

    for (int i = 1; i <= firstRow.length; i++) {
      headers[i] = Integer.toString(i - 1);
    }

    String[][] rows = new String[results.size()][firstRow.length + 1];

    int ri = 0;

    for (Entry<String, long[]> entry : results.entrySet()) {
      String[] row = new String[firstRow.length + 1];

      row[0] = entry.getKey();

      for (int i = 0; i < entry.getValue().length; i++) {
        row[i + 1] = Long.toString(entry.getValue()[i]);
      }

      rows[ri++] = row;
    }

    writeCsv(headers, rows, filePath);
  }

  private void writeCsv(String[] headers, String[][] rows, String filePath) throws IOException {

    BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));

    csvPrinter.printRecords(rows);
    csvPrinter.close();
  }


  private void writeQueries(Map<String, String> queries, String filePath) throws IOException {
    PrintWriter printWriter = new PrintWriter(filePath);

    for (Entry<String, String> entry : queries.entrySet()) {
      printWriter.printf("%3s - %s%n", entry.getKey(), entry.getValue());
    }

    printWriter.close();
  }
}
