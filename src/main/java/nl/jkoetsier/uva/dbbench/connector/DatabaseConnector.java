package nl.jkoetsier.uva.dbbench.connector;

import java.io.File;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.ApplicationConfigProperties;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

  @Autowired
  protected DbConfigProperties dbConfigProperties;

  @Autowired
  protected ApplicationConfigProperties applicationConfigProperties;

  public abstract void connect() throws DatabaseException;

  public abstract void executeQuery(ExecutableQuery query) throws DatabaseException;

  public abstract QueryResult getLastResults() throws DatabaseException;

  public abstract void importSchema(Schema schema) throws DatabaseException;

  protected abstract void importCsvFile(String tableName, String file) throws DatabaseException;

  public abstract long getTableSize(String tableName) throws DatabaseException;

  public abstract void closeConnection();

  public abstract HashMap<String, ? extends ExecutableQuery> getWorkloadQueries(Workload workload);

  public abstract HashMap<String, ? extends ExecutableQuery> getCreateQueries(Schema schema);

  public abstract String getSimpleName();

  /**
   * Transforms the given QueryResults to match the actual database output. May be an empty
   * function.
   *
   * Example; Postgres returns false values as 0. Comparing false and 0 will fail. This method
   * provides the possibility to change the result or expected result. QueryResult.replaceValues()
   * could come in handy.
   */
  public abstract void translateQueryResults(QueryResult queryResult, QueryResult expectedResult);

  /**
   * Imports CSV files from directory. Assumes the filename until the first dot is the table name.
   * Parts after the first dot will be ignored. Can be used as sequence number.
   */
  public void importCsvData(String directory) throws DatabaseException {
    File dir = new File(directory);
    File[] files = dir.listFiles();

    if (files != null) {

      for (File file : files) {
        if (file.isFile() && file.getName().endsWith(".csv")) {
          String[] fileNameParts = file.getName().split("\\.");

          importCsvFile(fileNameParts[0].toLowerCase(), file.getAbsolutePath());

          File renamedFile = new File(file.getAbsolutePath() + ".done");

          if (file.renameTo(renamedFile)) {
            logger.debug("Renamed file {} to {}",
                file.getAbsolutePath(),
                renamedFile.getAbsolutePath()
            );
          } else {
            logger.debug("Could not rename file {}", file.getAbsolutePath());
          }
        }
      }
    } else {
      throw new RuntimeException(String.format("Could not read data from %s", directory));
    }
  }

  public boolean isDocker() {
    return dbConfigProperties.isDocker();
  }

}
