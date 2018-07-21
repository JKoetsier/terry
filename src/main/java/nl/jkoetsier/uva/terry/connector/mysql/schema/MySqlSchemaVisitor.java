package nl.jkoetsier.uva.terry.connector.mysql.schema;

import nl.jkoetsier.uva.terry.connector.ColumnDef;
import nl.jkoetsier.uva.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.connector.SqlSchemaVisitor;
import nl.jkoetsier.uva.terry.connector.mysql.MySqlIdentifierQuoter;
import nl.jkoetsier.uva.terry.intrep.SqlQuery;
import nl.jkoetsier.uva.terry.intrep.schema.Table;
import nl.jkoetsier.uva.terry.intrep.schema.column.DateTimeTimezoneColumn;
import nl.jkoetsier.uva.terry.intrep.schema.column.VarCharColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlSchemaVisitor extends SqlSchemaVisitor {

  private static Logger logger = LoggerFactory.getLogger(MySqlSchemaVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MySqlIdentifierQuoter();
  }

  @Override
  public void visit(DateTimeTimezoneColumn dateTimeOffsetField) {
    ColumnDef columnDef = createColumnDef(dateTimeOffsetField, "TIMESTAMP");

    if (!columnDef.isNull()) {
      columnDef.setAppendedString(" DEFAULT CURRENT_TIMESTAMP");
    }

    columnDefStack.add(columnDef);
  }

  @Override
  public void visit(VarCharColumn varCharField) {
    ColumnDef columnDef;

    if (varCharField.getLength() == null) {
      // Comes from varchar(max)
      columnDef = createColumnDef(varCharField, "TEXT");
    } else {
      columnDef = createColumnDef(varCharField, "VARCHAR");
    }

    columnDefStack.add(columnDef);
  }

  @Override
  public void visit(Table table) {
    String createTable = String.format(
        "CREATE TABLE %s (", quoteIdentifier(table.getName())
    );

    for (ColumnDef columnDef : columnDefStack) {
      String arguments = "";

      if (columnDef.getArguments() != null) {
        arguments = String.format(
            "(%s)",
            String.join(", ", columnDef.getArguments())
        );
      }

      createTable = createTable.concat(String.format(
          "\n\t%s %s%s%s %s%s,",
          quoteIdentifier(columnDef.getName()),
          columnDef.getType(),
          arguments,
          columnDef.isAutoGenerated() ? " AUTO_INCREMENT" : "",
          columnDef.isNull() ? "NULL" : "NOT NULL",
          columnDef.getAppendedString() != null ? columnDef.getAppendedString() : ""
      ));
    }

    if (table.getPrimaryKey() == null) {
      boolean found = false;

      // MySQL needs AUTO_INCREMENT field to be PRIMARY KEY
      for (ColumnDef columnDef : columnDefStack) {
        if (columnDef.isAutoGenerated()) {
          createTable = createTable.concat(String.format(
              "\n\tPRIMARY KEY(\n\t\t%s\n\t)",
              columnDef.getName()
          ));
          found = true;
          break;
        }
      }

      if (!found) {
        // Remove comma
        createTable = createTable.substring(0, createTable.length() - 1);
      }
    } else {
      createTable = createTable.concat(String.format(
          "\n\tPRIMARY KEY (\n\t\t%s\n\t)",
          String.join(",\n\t\t", quoteIdentifiers(table.getPrimaryKeyFieldNames()))
      ));
    }

    createTable = createTable.concat("\n);");

    createQueries.put(table.getName(), new SqlQuery(createTable));
    columnDefStack.clear();
  }
}
