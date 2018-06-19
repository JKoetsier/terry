package nl.jkoetsier.uva.dbbench.internal.workload.query;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import org.junit.Test;

public class BinaryRelationTest {

  @Test
  public void addMaps() {
    HashMap<Table, Integer> mapA = new HashMap<>();
    HashMap<Table, Integer> mapB = new HashMap<>();
    Table tableA = new Table("tableA");
    Table tableB = new Table("tableB");
    Table tableC = new Table("tableC");
    Table tableD = new Table("tableD");

    mapA.put(tableA, 3);
    mapA.put(tableB, 2);
    mapA.put(tableC, 1);
    mapB.put(tableB, 1);
    mapB.put(tableC, 1);
    mapB.put(tableD, 3);

    HashMap<Table, Integer> expected = new HashMap<>();
    expected.put(tableA, 3);
    expected.put(tableB, 3);
    expected.put(tableC, 2);
    expected.put(tableD, 3);

    assertEquals(expected, BinaryRelation.addMaps(mapA, mapB));
  }
}