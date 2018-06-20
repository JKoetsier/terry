package nl.jkoetsier.uva.dbbench.internal.workload.meta;

import static org.junit.Assert.*;

import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import org.junit.Test;

public class TableCountsTest {

  @Test
  public void addItem() {
    TableCounts tableCounts = new TableCounts();
    Table tableA = new Table("tableA");
    Table tableB = new Table("tableB");
    tableCounts.add(tableA, 1);

    assertEquals(1, (int) tableCounts.get(tableA));
    tableCounts.add(tableA, 3);
    assertEquals(4, (int) tableCounts.get(tableA));

    tableCounts.add(tableB, 2);
    assertEquals(2, (int) tableCounts.get(tableB));
  }

  @Test
  public void addObject() {
    TableCounts ttA = new TableCounts();
    TableCounts ttB = new TableCounts();
    Table tableA = new Table("tableA");
    Table tableB = new Table("tableB");
    Table tableC = new Table("tableC");

    ttA.add(tableA, 2);
    ttA.add(tableB, 2);
    ttB.add(tableA, 1);
    ttB.add(tableC, 3);

    ttA.add(ttB);

    assertEquals(3, (int)ttA.get(tableA));
    assertEquals(2, (int)ttA.get(tableB));
    assertEquals(3, (int)ttA.get(tableC));
  }

  @Test
  public void addObjects() {
    TableCounts ttA = new TableCounts();
    TableCounts ttB = new TableCounts();
    Table tableA = new Table("tableA");
    Table tableB = new Table("tableB");
    Table tableC = new Table("tableC");
    Table tableD = new Table("tableD");

    ttA.add(tableA, 3);
    ttA.add(tableB, 2);
    ttA.add(tableC, 1);
    ttB.add(tableB, 1);
    ttB.add(tableC, 1);
    ttB.add(tableD, 3);

    TableCounts added = TableCounts.add(ttA, ttB);

    assertEquals(3, (int)added.get(tableA));
    assertEquals(3, (int)added.get(tableB));
    assertEquals(2, (int)added.get(tableC));
    assertEquals(3, (int)added.get(tableD));

  }
}