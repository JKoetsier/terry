package nl.jkoetsier.uva.dbbench.input.workload.sql;

import nl.jkoetsier.uva.dbbench.workload.query.Selection;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SelectItemVisitorTest {


  @Test
  public void testIsValidTableIdentifier() {
    HashMap<String, Boolean> inputs = new HashMap<>();
    inputs.put("a", true);
    inputs.put(".a", false);
    inputs.put("a.*", false); // Is valid, but should never reach the method
    inputs.put("a.b", true);
    inputs.put("adadfadf.d", true);
    inputs.put("b.adadfadf", true);
    inputs.put("a.#", false);
    inputs.put("#", false);

    SelectItemVisitor selectItemVisitor = new SelectItemVisitor(new Selection());

    for (Map.Entry<String, Boolean> entry : inputs.entrySet()) {
      assertEquals(String.format("Test %s for validity", entry.getKey()),
          selectItemVisitor.isValidTableIdentifier(entry.getKey()), entry.getValue());
    }
  }
}