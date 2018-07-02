package nl.jkoetsier.uva.terry.input;

import nl.jkoetsier.uva.terry.internal.schema.Schema;

public interface SchemaReader {

  Schema fromFile(String fileName);
}
