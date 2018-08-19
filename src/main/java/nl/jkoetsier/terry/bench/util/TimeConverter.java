package nl.jkoetsier.terry.bench.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeConverter {

  private static Logger logger = LoggerFactory.getLogger(TimeConverter.class);

  public static long nanoToMicro(long nano) {
    return nano / 1000;
  }

  public static long nanoToMilliSeconds(long nano) {
    return nanoToMicro(nano) / 1000;
  }

  public static String formatTimeNanoToMicro(long nanoTime) {
    return String.format("%d\u00B5s", nanoToMicro(nanoTime));
  }

}
