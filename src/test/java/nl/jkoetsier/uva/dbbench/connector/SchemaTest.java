package nl.jkoetsier.uva.dbbench.connector;

import org.junit.Test;

public interface SchemaTest {

  @Test
  void testCreateTable();

  @Test
  void testCreateTableArguments();

  @Test
  void testCreateTableCompositePrimaryKey();

  @Test
  void testCreateTableNullNotNull();

  @Test
  void testCreateTables();
}
