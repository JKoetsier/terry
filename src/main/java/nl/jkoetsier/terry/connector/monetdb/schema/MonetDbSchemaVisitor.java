package nl.jkoetsier.terry.connector.monetdb.schema;

import nl.jkoetsier.terry.connector.ColumnDef;
import nl.jkoetsier.terry.connector.SqlIdentifierQuoter;
import nl.jkoetsier.terry.connector.SqlSchemaVisitor;
import nl.jkoetsier.terry.connector.monetdb.MonetDbIdentifierQuoter;
import nl.jkoetsier.terry.intrep.SqlQuery;
import nl.jkoetsier.terry.intrep.schema.Table;
import nl.jkoetsier.terry.intrep.schema.column.DateTimeColumn;
import nl.jkoetsier.terry.intrep.schema.column.DateTimeTimezoneColumn;
import nl.jkoetsier.terry.intrep.schema.column.VarCharColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetDbSchemaVisitor extends SqlSchemaVisitor {

  private static Logger logger = LoggerFactory.getLogger(MonetDbSchemaVisitor.class);

  @Override
  protected SqlIdentifierQuoter getIdentifierQuoter() {
    return new MonetDbIdentifierQuoter();
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
  public void visit(DateTimeColumn dateTimeColumn) {
    columnDefStack.add(createColumnDef(dateTimeColumn, "TIMESTAMP"));
  }

  @Override
  public void visit(DateTimeTimezoneColumn dateTimeOffsetField) {
    columnDefStack.add(createColumnDef(dateTimeOffsetField, "TIMESTAMP WITH TIME ZONE"));
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
          "\n\t%s %s%s%s %s,",
          quoteIdentifier(columnDef.getName()),
          columnDef.isAutoGenerated() ? "int" : columnDef.getType(),
          arguments,
          columnDef.isAutoGenerated() ? " GENERATED ALWAYS AS IDENTITY" : "",
          columnDef.isNull() ? "NULL" : "NOT NULL"
      ));
    }

    if (table.getPrimaryKey() != null) {
      createTable = createTable.concat(String.format(
          "\n\tCONSTRAINT \"%sPrimaryKey\" PRIMARY KEY (\n\t\t%s\n\t)",
          table.getName(),
          String.join(",\n\t\t", quoteIdentifiers(table.getPrimaryKeyFieldNames())
          )));
    } else if (createTable.charAt(createTable.length() - 1) == ',') {
      createTable = createTable.substring(0, createTable.length() - 1);
    }

    createTable = createTable.concat("\n);");

    createQueries.put(table.getName(), new SqlQuery(createTable));
    columnDefStack.clear();
  }

}