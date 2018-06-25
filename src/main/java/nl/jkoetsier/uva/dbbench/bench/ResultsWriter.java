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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStatsCollectionWriter;
import nl.jkoetsier.uva.dbbench.bench.statistics.QueryStatistics;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultsWriter {

  private static Logger logger = LoggerFactory.getLogger(ResultsWriter.class);

  private String outputDirectory;
  private String dbIdentifier;
  private String dateTimeStr;

  public ResultsWriter(String outputDirectory, String dbIdentifier) {
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

  public void writeResults(Workload workload,
      Schema schema, Map<String, ExecutableQuery> queries,
      Map<String, long[]> results)
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

    String queryStatsFilename = String.format("%s/%s_%s_querystats.csv",
        outputDirectory,
        dateTimeStr,
        dbIdentifier
    );

    createDirectory();

    writeResults(results, outputCsvFilename);
    writeQueries(queries, outputQueriesFilename);
    writeQueryStats(workload, schema, queries, results, queryStatsFilename);
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

  private void writeQueryStats(Workload workload,
      Schema schema, Map<String, ExecutableQuery> queries,
      Map<String,
          long[]> results, String filePath) throws IOException {

    String[] headers = new String[]{
        "query",
        "avg",
        "touched_tables",
        "total_table_width",
        "total_table_widht_weighted",
        "touched_cnt",
        "columns_cnt",
        "table_sizes"
    };

    String[][] rows = new String[queries.size()][headers.length];

    int ri = 0;
    for (Entry<String, ExecutableQuery> entry : queries.entrySet()) {
      long[] queryResults = results.get(entry.getKey());
      Query query = workload.getQuery(entry.getKey());

      String[] row = new String[headers.length];

      row[0] = entry.getKey();
      row[1] = Double.toString(Arrays.stream(queryResults).average().getAsDouble());

      int sumTouched = 0;
      int sumWidth = 0;
      int sumWidthWeighted = 0;

      HashMap<String, Integer> tableWidths = new HashMap<>();
      HashMap<String, Integer> tableTouched = new HashMap<>();
      HashMap<String, Long> tableSizes = new HashMap<>();

      QueryStatistics queryStatistics = new QueryStatistics(query);

      // TODO move this logic to QueryStatistics
      for (Entry<Table, Integer> touchedTableEntry : queryStatistics.getTouchedTables().getAsMap()
          .entrySet()) {
        sumTouched += touchedTableEntry.getValue();
        sumWidth += touchedTableEntry.getKey().getColumnCnt();
        sumWidthWeighted +=
            touchedTableEntry.getValue() * touchedTableEntry.getKey().getColumnCnt();
        tableWidths
            .put(touchedTableEntry.getKey().getName(), touchedTableEntry.getKey().getColumnCnt());
        tableTouched.put(touchedTableEntry.getKey().getName(), touchedTableEntry.getValue());
        tableSizes
            .put(touchedTableEntry.getKey().getName(), touchedTableEntry.getKey().getRowCnt());
      }

      row[2] = Integer.toString(sumTouched);
      row[3] = Integer.toString(sumWidth);
      row[4] = Integer.toString(sumWidthWeighted);
      row[5] = tableTouched.toString();
      row[6] = tableWidths.toString();
      row[7] = tableSizes.toString();

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


  private void writeQueries(Map<String, ExecutableQuery> queries, String filePath) throws IOException {
    PrintWriter printWriter = new PrintWriter(filePath);

    for (Entry<String, ExecutableQuery> entry : queries.entrySet()) {
      printWriter.printf("%3s - %s%n", entry.getKey(), entry.getValue().toString());
    }

    printWriter.close();
  }
}
