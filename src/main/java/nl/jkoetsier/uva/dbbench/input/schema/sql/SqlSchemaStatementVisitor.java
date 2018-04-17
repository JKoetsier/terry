package nl.jkoetsier.uva.dbbench.input.schema.sql;

import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.schema.fields.FieldFactory;

public class SqlSchemaStatementVisitor extends StatementVisitorAdapter {

  private DataModel dataModel = DataModel.getInstance();

  public DataModel getDataModel() {
    return dataModel;
  }

  @Override
  public void visit(CreateTable createTable) {
    Entity entity = new Entity(createTable.getTable().getName());

    for (ColumnDefinition colDef : createTable.getColumnDefinitions()) {
      Field field = FieldFactory.create(colDef.getColDataType().getDataType());
      field.setName(colDef.getColumnName());
      entity.addField(field);
    }

    dataModel.addEntity(entity);

    super.visit(createTable);
  }
}
