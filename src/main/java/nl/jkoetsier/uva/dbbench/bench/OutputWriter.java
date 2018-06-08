package nl.jkoetsier.uva.dbbench.bench;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.LongStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputWriter {

  private static Logger logger = LoggerFactory.getLogger(OutputWriter.class);

  private String outputDirectory;
  private String dbIdentifier;

  public OutputWriter(String outputDirectory, String dbIdentifier) {
    this.outputDirectory = outputDirectory;
    this.dbIdentifier = dbIdentifier;
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

  public void writeResults(Map<Integer, String> queries, Map<Integer, long[]> results)
      throws IOException {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String dateTimeStr = now.format(dateTimeFormatter);

    String outputCsvFilename = String.format("%s/%s_%s.csv",
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

  private void writeResults(Map<Integer, long[]> results, String filePath) throws IOException {
    if (results.get(0) == null) {
      return;
    }

    long[] firstRow = results.get(0);
    String[] headers = new String[firstRow.length + 2];
    headers[0] = "query";
    headers[1] = "avg";

    for (int i = 2; i <= firstRow.length; i++) {
      headers[i] = Integer.toString(i - 2);
    }

    String[][] rows = new String[results.size()][firstRow.length + 2];

    int ri = 0;

    for (Entry<Integer, long[]> entry : results.entrySet()) {
      String[] row = new String[firstRow.length + 2];

      long avg = LongStream.of(entry.getValue()).sum() / entry.getValue().length;
      row[0] = Integer.toString(entry.getKey());
      row[1] = Long.toString(avg);

      for (int i = 0; i < entry.getValue().length; i++) {
        row[i + 2] = Long.toString(entry.getValue()[i]);
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


  private void writeQueries(Map<Integer, String> queries, String filePath) throws IOException {
    PrintWriter printWriter = new PrintWriter(filePath);

    for (Entry<Integer, String> entry : queries.entrySet()) {
      printWriter.printf("%3d - %s%n", entry.getKey(), entry.getValue());
    }

    printWriter.close();
  }
}
