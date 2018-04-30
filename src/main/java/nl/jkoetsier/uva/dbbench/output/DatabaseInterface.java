package nl.jkoetsier.uva.dbbench.output;

import nl.jkoetsier.uva.dbbench.internal.schema.Schema;

public interface DatabaseInterface {

  void start();

  void connect();

  void closeConnection();

  void importSchema(Schema schema);
}
