package nl.jkoetsier.uva.dbbench.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataHelper {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private String dataDirectory = "/data/";

  public String getFilePath(String filename) {
    String file = dataDirectory + filename;

    logger.info("Loading file {}", file);

    return getClass().getResource(file).getFile();
  }

}
