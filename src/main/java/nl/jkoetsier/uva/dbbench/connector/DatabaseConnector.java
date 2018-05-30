package nl.jkoetsier.uva.dbbench.connector;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DatabaseConnector {

  public abstract void connect() throws SQLException;

  public abstract void executeQuery(String query) throws SQLException;

  public abstract void importSchema(Schema schema) throws SQLException;

  protected abstract void importCsvFile(String tableName, String file) throws SQLException;

  /**
   * Imports CSV files from directory. Assumes the filename until the first dot is the table name.
   * Parts after the first dot will be ignored. Can be used as sequence number.
   *
   * @param directory
   * @throws SQLException
   */
  public void importCsvData(String directory) throws SQLException {
    File dir = new File(directory);
    File[] files = dir.listFiles();

    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile() && files[i].getName().endsWith(".csv")) {
        String[] fileNameParts = files[i].getName().split("\\.");

        importCsvFile(fileNameParts[0], files[i].getAbsolutePath());
      }
    }
  }

  public abstract void closeConnection();

  public abstract HashMap<Integer, String> getWorkloadQueries(Workload workload);

  public abstract HashMap<String, String> getCreateQueries(Schema schema);

  @Autowired
  protected DbConfigProperties dbConfigProperties;

  public boolean isDocker() {
    return dbConfigProperties.isDocker();
  }
}
