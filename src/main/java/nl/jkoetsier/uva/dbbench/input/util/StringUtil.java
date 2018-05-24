package nl.jkoetsier.uva.dbbench.input.util;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

  private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

  private static String unEscapeSingleIdentifier(String inputString) {
    if (inputString.charAt(0) == '[' && inputString.charAt(inputString.length() - 1) == ']') {
      return inputString.substring(1, inputString.length() - 1);
    }

    return inputString;
  }

  public static String unEscapeIdentifier(String inputString) {
    String[] splitted = inputString.split("\\.");

    ArrayList<String> resultParts = new ArrayList<>();

    for (String part : splitted) {
      resultParts.add(unEscapeSingleIdentifier(part));
    }

    return String.join(".", resultParts);
  }
}
