package nl.jkoetsier.uva.terry.bench.analyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import nl.jkoetsier.uva.terry.connector.DatabaseConnector;
import nl.jkoetsier.uva.terry.connector.DatabaseConnector.Direction;
import nl.jkoetsier.uva.terry.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.schema.Table;
import nl.jkoetsier.uva.terry.internal.schema.fields.Column;
import nl.jkoetsier.uva.terry.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexAnalyser {

  private static Logger logger = LoggerFactory.getLogger(IndexAnalyser.class);
  private static int INDEX_TABLE_MIN_SIZE = 0;
  private Schema schema;
  private Workload workload;
  private DatabaseConnector databaseConnector;

  public IndexAnalyser(DatabaseConnector databaseConnector, Schema schema, Workload workload) {
    this.schema = schema;
    this.workload = workload;
    this.databaseConnector = databaseConnector;
  }

  // TODO improve. Only works with schema's that have unique column names throughout the database
  // now, such as TPC-H.
  public void analyse() throws DatabaseException {

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
     databaseConnector.createIndex(tableColumn.getTable().getName(),
         tableColumn.getColumn().getName(), Direction.ASC);
  }
}
