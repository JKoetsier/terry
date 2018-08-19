package nl.jkoetsier.terry.bench.indexcreator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import nl.jkoetsier.terry.connector.DatabaseConnector;
import nl.jkoetsier.terry.connector.DatabaseConnector.Direction;
import nl.jkoetsier.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.schema.Table;
import nl.jkoetsier.terry.intrep.schema.column.Column;
import nl.jkoetsier.terry.intrep.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexCreator {

  private static Logger logger = LoggerFactory.getLogger(IndexCreator.class);
  private static int INDEX_TABLE_MIN_SIZE = 0;
  private Schema schema;
  private Workload workload;
  private DatabaseConnector databaseConnector;

  public IndexCreator(DatabaseConnector databaseConnector, Schema schema, Workload workload) {
    this.schema = schema;
    this.workload = workload;
    this.databaseConnector = databaseConnector;
  }

  // Limitation: Creates indices on all columns throughout the database with a given column name
  public void createIndices() throws DatabaseException {

    WorkloadAnalyserVisitor visitor = new WorkloadAnalyserVisitor();
    workload.acceptVisitor(visitor);

    HashMap<String, Integer> columnCounts = visitor.getColumnCounts();

    Set<TableColumn> createIndices = new HashSet<>();

    for (Entry<String, Integer> cntEntry : columnCounts.entrySet()) {

      Set<TableColumn> tableColumns = getTableColumnsForColumn(cntEntry.getKey());

      for (TableColumn tableColumn : tableColumns) {
        if (tableColumn != null && tableColumn.getTable().getRowCnt() > INDEX_TABLE_MIN_SIZE) {
          createIndices.add(tableColumn);
        }
      }
    }

    for (TableColumn tableColumn : createIndices) {
      createIndex(tableColumn);
    }
  }

  private Set<TableColumn> getTableColumnsForColumn(String fullColumnName) {
    Set<TableColumn> tableColumns = new HashSet<>();
    String[] splitColumn = fullColumnName.split("\\.");
    String columnName = splitColumn[splitColumn.length - 1];

    for (Entry<String, Table> tableEntry : schema.getTables().entrySet()) {
      Table table = tableEntry.getValue();

      for (Entry<String, Column> columnEntry : table.getColumns().entrySet()) {
        Column column = columnEntry.getValue();

        String[] splitColumnName = column.getName().split("\\.");

        if (splitColumnName[splitColumnName.length - 1].equals(columnName)) {
          tableColumns.add(new TableColumn(table, column));
        }
      }
    }

    return tableColumns;
  }

  private void createIndex(TableColumn tableColumn) throws DatabaseException {
    try {
      databaseConnector.createIndex(tableColumn.getTable().getName(),
          tableColumn.getColumn().getName(), Direction.ASC);
    } catch (Exception e) {
      logger.error("Error creating index: {}", e.getMessage());
      throw new RuntimeException(e);
    }

  }
}
