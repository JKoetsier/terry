package nl.jkoetsier.terry.bench;

import nl.jkoetsier.terry.bench.util.TimeConverter;
import nl.jkoetsier.terry.connector.DatabaseConnector;
import nl.jkoetsier.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.terry.intrep.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitialiser {

  private static Logger logger = LoggerFactory.getLogger(DatabaseInitialiser.class);

  private DatabaseConnector databaseConnector;
  private Schema schema;
  private String dataDirectory;
  private boolean skipCreateSchema = false;

  public DatabaseInitialiser(DatabaseConnector databaseConnector, Schema schema) {
    this.databaseConnector = databaseConnector;
    this.schema = schema;
  }

  public void setSkipCreateSchema(boolean skipCreateSchema) {
    this.skipCreateSchema = skipCreateSchema;
  }

  public void setSchema(Schema schema) {
    this.schema = schema;
  }

  public void setDataDirectory(String dataDirectory) {
    this.dataDirectory = dataDirectory;
  }

  public void initialise() throws DatabaseException {
    initialiseSchema();

    if (dataDirectory != null) {
      importData();
    }
  }

  private void initialiseSchema() throws DatabaseException {
    if (!skipCreateSchema) {
      logger.info("Initialising schema");

      databaseConnector.createSchema(schema);
    } else {
      logger.info("Skipping creation of schema");
    }
  }

  private void importData() throws DatabaseException {
    logger.info("Importing data");

    long start = System.nanoTime();

    databaseConnector.importCsvData(dataDirectory);

    logger.info("Importing data took {} ms", TimeConverter.nanoToMilliSeconds(System.nanoTime() - start));
  }
}
