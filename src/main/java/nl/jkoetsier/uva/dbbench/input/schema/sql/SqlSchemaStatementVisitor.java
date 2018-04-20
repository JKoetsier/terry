package nl.jkoetsier.uva.dbbench.input.schema.sql;

import java.util.HashSet;
import java.util.Set;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.FieldFactory;

public class SqlSchemaStatementVisitor extends StatementVisitorAdapter {

  private Schema dataModel = Schema.getInstance();

  public Schema getDataModel() {
    return dataModel;
  }

  @Override
  public void visit(CreateTable createTable) {
    Entity entity = new Entity(createTable.getTable().getName());

    for (ColumnDefinition colDef : createTable.getColumnDefinitions()) {
      Field field = FieldFactory.create(colDef.getColDataType().getDataType());
      field.setName(colDef.getColumnName());

      if (colDef.getColDataType().getArgumentsStringList() != null) {
        field.setArguments(colDef.getColDataType().getArgumentsStringList());
      }

      String colSpecString = String.join(" ", colDef.getColumnSpecStrings());
      field.setAllowedEmpty(!colSpecString.contains("NOT NULL"));

      entity.addField(field);
    }

    if (createTable.getIndexes() != null) {
      setPrimaryKey(createTable, entity);
    }

    dataModel.addEntity(entity);
  }

  private void setPrimaryKey(CreateTable createTable, Entity entity) {
    for (Index index : createTable.getIndexes()) {
      if (index.getType().equals("PRIMARY KEY")) {

        Set<Field> columns = new HashSet<>();

        for (String columnName : index.getColumnsNames()) {
          Field field = entity.getField(columnName);

          if (field == null) {
            throw new InvalidQueryException(
                String.format("Field %s for primary key in table %s does not exist", columnName,
                    entity.getName())
            );
          }

          columns.add(field);
        }

        entity.setPrimaryKey(columns);
      }
    }
  }
}
