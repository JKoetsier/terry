package nl.jkoetsier.uva.dbbench.connector.util.csvlayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCsvLayout extends CsvLayout {

  private static Logger logger = LoggerFactory.getLogger(DefaultCsvLayout.class);

  @Override
  public String getFieldSeparator() {
    return ",";
  }

  @Override
  public String getFieldEnclosing() {
    return "\"";
  }

  @Override
  public boolean hasHeader() {
    return true;
  }
}
