package nl.jkoetsier.uva.dbbench.input.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class IdentifierValidatorTest {

  @Test
  public void isValidTableIdentifier() {
    HashMap<String, Boolean> inputs = new HashMap<>();
    inputs.put("a", true);
    inputs.put(".a", false);
    inputs.put("a.*", false); // Is valid, but should never reach the method
    inputs.put("a.b", true);
    inputs.put("adadfadf.d", true);
    inputs.put("b.adadfadf", true);
    inputs.put("a.#", false);
    inputs.put("#", false);

    for (Map.Entry<String, Boolean> entry : inputs.entrySet()) {
      assertEquals(String.format("Test %s for validity", entry.getKey()),
          IdentifierValidator.isValidTableIdentifier(entry.getKey()), entry.getValue());
    }
  }
}