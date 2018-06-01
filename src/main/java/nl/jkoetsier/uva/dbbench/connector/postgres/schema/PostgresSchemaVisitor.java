package nl.jkoetsier.uva.dbbench.connector.postgres.schema;

import nl.jkoetsier.uva.dbbench.connector.ColumnDef;
import nl.jkoetsier.uva.dbbench.connector.SqlIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.connector.SqlSchemaVisitor;
import nl.jkoetsier.uva.dbbench.connector.postgres.PostgresIdentifierQuoter;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.DecimalColumn;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.DoubleColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresSchemaVisitor extends SqlSchemaVisitor {

  private static Logger logger = LoggerFactory.getLogger(PostgresSchemaVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new PostgresIdentifierQuoter();
  }

  @Override
  public void visit(DecimalColumn decimalColumn) {
    columnDefStack.add(createColumnDef(decimalColumn, "NUMERIC"));
  }

  @Override
  public void visit(DoubleColumn doubleColumn) {
    columnDefStack.add(createColumnDef(doubleColumn, "FLOAT8"));
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
          "\n\t%s %s%s %s,",
          quoteIdentifier(columnDef.getName()),
          columnDef.isAutoGenerated() ? "SERIAL" : columnDef.getType(),
          arguments,
          columnDef.isNull() ? "NULL" : "NOT NULL"
      ));
    }

    if (table.getPrimaryKey() != null) {
      createTable = createTable.concat(String.format(
          "\n\tPRIMARY KEY (\n\t\t%s\n\t)",
          String.join(",\n\t\t", quoteIdentifiers(table.getPrimaryKeyFieldNames()))
      ));
    } else if (createTable.charAt(createTable.length() - 1) == ',') {
      createTable = createTable.substring(0, createTable.length() - 1);
    }

    createTable = createTable.concat("\n);");

    createQueries.put(table.getName(), createTable);
    columnDefStack.clear();
  }
}
