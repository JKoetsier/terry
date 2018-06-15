package nl.jkoetsier.uva.dbbench.connector.util.valuetranslator;

import nl.jkoetsier.uva.dbbench.connector.ValueTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveLineBreaksValueTranslator implements ValueTranslator {

  private static Logger logger = LoggerFactory.getLogger(RemoveLineBreaksValueTranslator.class);

  @Override
  public String translate(String input) {
    return input.replace("\n", " ").replace("\r", "");
  }
}

