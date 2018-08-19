package nl.jkoetsier.terry.input;

import nl.jkoetsier.terry.intrep.schema.Schema;

public interface SchemaReader {

  Schema fromFile(String fileName);
}
