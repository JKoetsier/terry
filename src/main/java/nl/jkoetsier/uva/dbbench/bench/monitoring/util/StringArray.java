package nl.jkoetsier.uva.dbbench.bench.monitoring.util;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringArray {

  private static Logger logger = LoggerFactory.getLogger(StringArray.class);

  public static String[] concatArrays(String[]... arrays) {
    ArrayList<String> result = new ArrayList(Arrays.asList(arrays[0]));

    for (int i = 1; i < arrays.length; i++) {
      result.addAll(Arrays.asList(arrays[i]));
    }

    return result.toArray(new String[0]);
  }
}
