package nl.jkoetsier.terry.connector.postgres;

import static org.junit.Assert.assertEquals;

import nl.jkoetsier.terry.connector.util.valuetranslator.DateTimeValueTranslator;
import org.junit.Test;

public class DateTimeValueTranslatorTest {

  private DateTimeValueTranslator translator = new DateTimeValueTranslator();

  @Test
  public void translate() {
    testTranslateDate("2018-04-28 13:08:22.8550420 +02:00", "2018-04-28 13:08:22.0");
    testTranslateDate("NULL,2018-04-28 14:09:41.6182307 +02:00,2018-04-28 14:09:41.6182307 +02:00,15311",
        "NULL,2018-04-28 14:09:41.0,2018-04-28 14:09:41.0,15311");
  }

  public void testTranslateDate(String input, String output) {
    assertEquals(output, translator.translate(input));
  }
}