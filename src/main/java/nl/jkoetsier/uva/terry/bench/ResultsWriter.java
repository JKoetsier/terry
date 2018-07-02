package nl.jkoetsier.uva.terry.bench;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.uva.terry.bench.monitoring.stats.SystemStatsCollection;
import nl.jkoetsier.uva.terry.bench.monitoring.stats.SystemStatsCollectionWriter;
import nl.jkoetsier.uva.terry.bench.results.Results;
import nl.jkoetsier.uva.terry.bench.results.RunResult;
import nl.jkoetsier.uva.terry.bench.results.RunResultList;
import nl.jkoetsier.uva.terry.bench.results.RunResultStringValueSupplier;
import nl.jkoetsier.uva.terry.bench.statistics.QueryStatistics;
import nl.jkoetsier.uva.terry.internal.ExecutableQuery;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.workload.Query;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultsWriter {

  private static Logger logger = LoggerFactory.getLogger(ResultsWriter.class);

  private String outputDirectory;
  private String dbIdentifier;
  private String dateTimeStr;
  private Schema schema;

  public ResultsWriter(String outputDirectory, String dbIdentifier, Schema schema) {
    this.outputDirectory = outputDirectory;
    this.dbIdentifier = dbIdentifier;
    this.schema = schema;

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

  public void writeResults(Results results)
      throws IOException {

    String outputResponseTimesFilename = String.format("%s/%s_%s_responsetimes.csv",
        outputDirectory,
        dateTimeStr,
        dbIdentifier
    );

    String outputResultTimesFilename = String.format("%s/%s_%s_resulttimes.csv",
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

    writeResponseTimes(results, outputResponseTimesFilename);
    writeResultTimes(results, outputResultTimesFilename);
    writeQueries(results, outputQueriesFilename);
    writeQueryStats(results, queryStatsFilename);
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


  /**
   * Writes a CSV matrix of values from a RunResult.
   * Value should be provided by a RunResultStringValueSupplier
   *
   * @param results Results from a bench run
   * @param valueSupplier Provides the value to print to CSV
   * @param filePath Path to write the CSV to
   * @throws IOException When something goes wrong with IO
   */
  private void writeRunResultValuesMatrix(Results results, RunResultStringValueSupplier valueSupplier, String filePath) throws IOException {

    if (results.size() == 0) {
      return;
    }

    List<ExecutableQuery> keys = results.keys();
    RunResultList firstRow = results.get(keys.get(0));

    String[] headers = new String[firstRow.size() + 1];
    headers[0] = "query";

    for (int i = 1; i <= firstRow.size(); i++) {
      headers[i] = Integer.toString(i - 1);
    }

    String[][] rows = new String[results.size()][firstRow.size() + 1];

    int ri = 0;

    for (Entry<ExecutableQuery, RunResultList> entry : results.getResults().entrySet()) {
      String[] row = new String[firstRow.size() + 1];

      row[0] = entry.getKey().getIdentifier();

      for (int i = 0; i < entry.getValue().size(); i++) {
        RunResult runResult = entry.getValue().get(i);
        row[i + 1] = valueSupplier.getValue(runResult);
      }

      rows[ri++] = row;
    }

    writeCsv(headers, rows, filePath);
  }

  private void writeResponseTimes(Results results, String filePath) throws IOException {
    RunResultStringValueSupplier vs = (r) -> (Long.toString(r.getResponseTimeNs()));

    writeRunResultValuesMatrix(results, vs, filePath);
  }

  private void writeResultTimes(Results results, String filePath) throws IOException {
    RunResultStringValueSupplier vs = (r) -> (Long.toString(r.getResultsTimeNs()));

    writeRunResultValuesMatrix(results, vs, filePath);
  }

  private void writeQueryStats(Results results, String filePath) throws IOException {

    String[] headers = new String[]{
        "query",
        "avg_response",
        "avg_results",
        "result_rows",
        "result_width",
        "touched_tables",
        "total_table_width",
        "total_table_widht_weighted",
        "touched_cnt",
        "columns_cnt",
        "table_sizes"
    };

    String[][] rows = new String[results.size()][headers.length];

    int ri = 0;

    for (Entry<ExecutableQuery, RunResultList> entry : results.getResults().entrySet()) {
      Query query = entry.getKey().getQueryObject();


      String[] row = new String[headers.length];

      int curIndex = 0;

      RunResult firstResult = entry.getValue().get(0);

      row[curIndex++] = entry.getKey().getIdentifier(); // query
      row[curIndex++] = Double.toString(entry.getValue().getAverageResponseTime()); // avg_response
      row[curIndex++] = Double.toString(entry.getValue().getAverageResultsTime()); // avg_results
      row[curIndex++] = Integer.toString(firstResult.getResultLength()); // result_rows
      row[curIndex++] = Integer.toString(firstResult.getResultWidth()); // result_width

      QueryStatistics queryStatistics = new QueryStatistics(query, schema);

      row[curIndex++] = Integer.toString(queryStatistics.getSumTablesTouched()); // touched_tables
      row[curIndex++] = Integer.toString(queryStatistics.getSumTableWidth()); // total_table_width
      row[curIndex++] = Integer.toString(queryStatistics.getSumTableWidthWeighted()); // total_table_width_weighted
      row[curIndex++] = queryStatistics.getTablesTouched().toString(); // touched_cnt
      row[curIndex++] = queryStatistics.getTableWidths().toString(); // columns_cnt
      row[curIndex] = queryStatistics.getTableLengths().toString(); // table_sizes

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


  private void writeQueries(Results results, String filePath) throws IOException {
    PrintWriter printWriter = new PrintWriter(filePath);

    for (Entry<ExecutableQuery, RunResultList> entry : results.getResults().entrySet()) {
      printWriter.printf("%3s - %s%n", entry.getKey().getIdentifier(), entry.getKey().toString());
    }

    printWriter.close();
  }
}
