package nl.jkoetsier.uva.terry.bench.analyser;

import java.util.Objects;
import nl.jkoetsier.uva.terry.intrep.schema.Table;
import nl.jkoetsier.uva.terry.intrep.schema.column.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableColumn {

  private static Logger logger = LoggerFactory.getLogger(TableColumn.class);

  private Table table;
  private Column column;

  public TableColumn(Table table, Column column) {
    this.table = table;
    this.column = column;
  }

  public Table getTable() {
    return table;
  }

  public void setTable(Table table) {
    this.table = table;
  }

  public Column getColumn() {
    return column;
  }

  public void setColumn(Column column) {
    this.column = column;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TableColumn that = (TableColumn) o;
    return Objects.equals(table, that.table) &&
        Objects.equals(column, that.column);
  }

  @Override
  public int hashCode() {

    return Objects.hash(table, column);
  }
}
