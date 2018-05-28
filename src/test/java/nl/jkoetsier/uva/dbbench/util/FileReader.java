package nl.jkoetsier.uva.dbbench.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReader {

  private static Logger logger = LoggerFactory.getLogger(FileReader.class);

  public static String readAsString(String filename) {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(filename));

      return new String(encoded, Charset.defaultCharset());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
