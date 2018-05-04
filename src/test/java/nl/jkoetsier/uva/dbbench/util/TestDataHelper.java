package nl.jkoetsier.uva.dbbench.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataHelper {

  private static Logger logger = LoggerFactory.getLogger(TestDataHelper.class);
  private String dataDirectory = "/data/";

  public String getFilePath(String filename) {
    String file = dataDirectory + filename;

    logger.info("Loading file {}", file);

    return getClass().getResource(file).getFile();
  }

  // Removes all unnecessary whitespace, only keeps one space.
  public String normalise(String str) {
    return str.replaceAll("\\s+", " ");
  }
}
