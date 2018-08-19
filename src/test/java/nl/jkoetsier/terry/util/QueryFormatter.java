package nl.jkoetsier.terry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryFormatter {

  private static Logger logger = LoggerFactory.getLogger(QueryFormatter.class);

  public static String format(String query) {
    // Prepend newlines
    String result = query
        .replaceAll("(?i)(SELECT|FROM|ORDER|WHERE|OFFSET|AND|LEFT|INNER|ON )", "\n$1");

    // Append newlines
    result = result.replaceAll("(?i)(SELECT|,)", "$1\n");

    // Remove duplicate newlines
    result = result.replaceAll("(\n)+", "\n");
    return result;
  }
}
