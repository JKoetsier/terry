package nl.jkoetsier.uva.dbbench.output;

import nl.jkoetsier.uva.dbbench.internal.schema.Schema;

public interface DatabaseInterface {

  void connect();
  void dropTable(String table);
  void executeQuery(String query);
  void importSchema(Schema schema);
  void closeConnection();

}
