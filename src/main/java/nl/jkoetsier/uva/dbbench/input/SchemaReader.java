package nl.jkoetsier.uva.dbbench.input;

import nl.jkoetsier.uva.dbbench.schema.Schema;

public interface SchemaReader {

  Schema fromFile(String fileName);
}
