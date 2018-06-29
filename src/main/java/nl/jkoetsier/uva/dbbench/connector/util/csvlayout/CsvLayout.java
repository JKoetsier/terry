package nl.jkoetsier.uva.dbbench.connector.util.csvlayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CsvLayout {

  private static Logger logger = LoggerFactory.getLogger(CsvLayout.class);

  public abstract String getFieldSeparator();
  public abstract String getFieldEnclosing();
  public abstract boolean hasHeader();

  public String getLineTerminator() {
    return "\\n";
  }
}
