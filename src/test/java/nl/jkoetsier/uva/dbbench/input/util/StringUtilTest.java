package nl.jkoetsier.uva.dbbench.input.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;
import org.junit.Test;

public class StringUtilTest {

  private HashMap<String, String> results = new HashMap<>();

  public StringUtilTest() {
    results.put("[testIdentifier]", "testIdentifier");
    results.put("testIdentifier", "testIdentifier");
    results.put("[testIdentifier", "[testIdentifier");
    results.put("testIdentifier]", "testIdentifier]");
    results.put("t[estIdentifier]", "t[estIdentifier]");
    results.put("[testIdentifie]r", "[testIdentifie]r");
    results.put("[bla].lala", "bla.lala");
    results.put("blaa.[lala]", "blaa.lala");
    results.put("[bla.lala]", "[bla.lala]");
  }

  @Test
  public void unEscapeIdentifier() {
    for (Entry<String, String> entry : results.entrySet()) {
      String result = StringUtil.unEscapeIdentifier(entry.getKey());

      assertEquals(String.format("Expected result for %s is %s. Got %s",
          entry.getKey(), entry.getValue(), result), entry.getValue(), result);
    }
  }
}