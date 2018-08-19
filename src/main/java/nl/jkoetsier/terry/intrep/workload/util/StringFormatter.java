package nl.jkoetsier.terry.intrep.workload.util;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringFormatter {

  private static Logger logger = LoggerFactory.getLogger(StringFormatter.class);

  public static String format(String input) {
    StringBuilder stringBuilder = new StringBuilder();

    String intermediate = input;

    int depth = 0;
    int depthDiff = 0;
    char[] chars = {'}', '{', ',', '[', ']'};
    breakLocation breakPoint = breakLocation.NEXTLINE;

    int nextPos = nextCharPos(intermediate, chars);

    while (nextPos != -1) {

      switch (intermediate.charAt(nextPos)) {
        case '[':
        case '{':
          breakPoint = breakLocation.SAMELINE;
          depthDiff = 1;
          break;
        case ']':
        case '}':
          breakPoint = breakLocation.NEXTLINE;
          depthDiff = -1;
          break;
        case ',':
          breakPoint = breakLocation.SAMELINE;
          depthDiff = 0;
          break;
      }

      switch (breakPoint) {
        case SAMELINE:
          stringBuilder.append(intermediate.substring(0, nextPos + 1));
          stringBuilder.append('\n');
          depth += depthDiff;
          stringBuilder.append(nTabs(depth));
          intermediate = intermediate.substring(nextPos + 1);
          break;
        case NEXTLINE:
          stringBuilder.append(intermediate.substring(0, nextPos));
          stringBuilder.append('\n');
          depth += depthDiff;
          stringBuilder.append(nTabs(depth));
          stringBuilder.append(intermediate.charAt(nextPos));

          intermediate = intermediate.substring(nextPos + 1);

          break;
      }

      intermediate = intermediate.trim();

      nextPos = nextCharPos(intermediate, chars);
    }

    return stringBuilder.toString();
  }

  private static int nextCharPos(String str, char[] chars) {
    int nextPos = -1;

    for (char c : chars) {
      int charPos = str.indexOf(c);

      if (nextPos == -1 || (charPos != -1 && charPos < nextPos)) {
        nextPos = charPos;
      }
    }

    return nextPos;
  }

  private static String nTabs(int n) {
    char[] chars = new char[n];
    Arrays.fill(chars, '\t');
    return new String(chars);
  }

  private enum breakLocation {SAMELINE, NEXTLINE}
}
