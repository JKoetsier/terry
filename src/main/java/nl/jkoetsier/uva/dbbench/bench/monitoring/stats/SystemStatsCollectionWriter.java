package nl.jkoetsier.uva.dbbench.bench.monitoring.stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemStatsCollectionWriter {

  private static Logger logger = LoggerFactory.getLogger(SystemStatsCollectionWriter.class);

  public void writeCsv(SystemStatsCollection statsCollection, String filePath) throws IOException {
    if (statsCollection.getAll().size() == 0) {
      logger.warn("No stats to output in SystemStatsCollection");
      return;
    }

    String[] headers = statsCollection.getAll().get(0).getHeaders();

    BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));

    for (SystemStats systemStats : statsCollection.getAll()) {
      csvPrinter.printRecord(systemStats.getValues());
    }

    csvPrinter.close();
  }
}
