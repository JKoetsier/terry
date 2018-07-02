package nl.jkoetsier.uva.terry.connector.util.valuetranslator;

import nl.jkoetsier.uva.terry.connector.ValueTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveLineBreaksValueTranslator implements ValueTranslator {

  private static Logger logger = LoggerFactory.getLogger(RemoveLineBreaksValueTranslator.class);

  @Override
  public String translate(String input) {
    return input.replace("\n", " ").replace("\r", "");
  }
}

