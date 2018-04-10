package nl.jkoetsier.uva.dbbench.input;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;

public interface SchemaReader {

    DataModel fromFile(String fileName);
}
