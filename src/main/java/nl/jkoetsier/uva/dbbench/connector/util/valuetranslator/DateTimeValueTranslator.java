package nl.jkoetsier.uva.dbbench.connector.util.valuetranslator;

import nl.jkoetsier.uva.dbbench.connector.ValueTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeValueTranslator implements ValueTranslator {

  private static Logger logger = LoggerFactory.getLogger(DateTimeValueTranslator.class);

  /**
   * Translates dates of the form '2018-04-28 13:08:22.8550420 +02:00' to dates of the form
   * '2018-04-28 13:08:22.0'
   */
  public String translate(String input) {
    return input
        .replaceAll("(?<date>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\.\\d+ \\+\\d{2}:\\d{2}",
            "${date}.0");
  }
}
