package nl.jkoetsier.uva.terry.input;

import nl.jkoetsier.uva.terry.intrep.schema.Schema;

public interface SchemaReader {

  Schema fromFile(String fileName);
}
