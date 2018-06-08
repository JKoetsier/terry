package nl.jkoetsier.uva.dbbench.connector;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SqlIdentifierQuoter {

  private static Logger logger = LoggerFactory.getLogger(SqlIdentifierQuoter.class);

  public abstract char getOpenChar();

  public abstract char getCloseChar();

  public String quoteString(String input) {
    return String.format("%c%s%c", getOpenChar(), input, getCloseChar());
  }

  public List<String> quoteStrings(List<String> inputList) {
    List<String> resultList = new ArrayList<>();

    for (String inputStr : inputList) {
      resultList.add(quoteString(inputStr));
    }

    return resultList;
  }
}
