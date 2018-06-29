package nl.jkoetsier.uva.dbbench.connector.util.csvlayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TpcCsvLayout extends CsvLayout {

  private static Logger logger = LoggerFactory.getLogger(TpcCsvLayout.class);

  @Override
  public String getFieldSeparator() {
    return "|";
  }

  @Override
  public String getFieldEnclosing() {
    return "";
  }

  @Override
  public boolean hasHeader() {
    return false;
  }
}
