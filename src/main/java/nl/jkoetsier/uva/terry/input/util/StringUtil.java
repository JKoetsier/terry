package nl.jkoetsier.uva.terry.input.util;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

  private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

  private static char[][] quotePairs = new char[][]{
      {'[', ']'},
      {'"', '"'},
      {'`', '`'}
  };

  /**
   * Unquotes identifier based on quotation marks defined in quotePairs
   */
  private static String unEscapeIdentifierPart(String inputString) {
    for (int i = 0; i < quotePairs.length; i++) {
      if (inputString.charAt(0) == quotePairs[i][0] &&
          inputString.charAt(inputString.length() - 1) == quotePairs[i][1]) {

        return inputString.substring(1, inputString.length() - 1);
      }
    }

    return inputString;
  }

  public static String unEscapeIdentifier(String inputString) {
    String[] splitted = inputString.split("\\.");

    ArrayList<String> resultParts = new ArrayList<>();

    for (String part : splitted) {
      resultParts.add(unEscapeIdentifierPart(part));
    }

    return String.join(".", resultParts);
  }
}
