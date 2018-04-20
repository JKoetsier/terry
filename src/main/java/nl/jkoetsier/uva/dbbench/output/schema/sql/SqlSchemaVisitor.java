package nl.jkoetsier.uva.dbbench.output.schema.sql;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.Schema;
import nl.jkoetsier.uva.dbbench.schema.fields.BigIntegerField;
import nl.jkoetsier.uva.dbbench.schema.fields.BooleanField;
import nl.jkoetsier.uva.dbbench.schema.fields.CharField;
import nl.jkoetsier.uva.dbbench.schema.fields.DateField;
import nl.jkoetsier.uva.dbbench.schema.fields.DateTimeField;
import nl.jkoetsier.uva.dbbench.schema.fields.DateTimeTimezoneField;
import nl.jkoetsier.uva.dbbench.schema.fields.DecimalField;
import nl.jkoetsier.uva.dbbench.schema.fields.DoubleField;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.schema.fields.FloatField;
import nl.jkoetsier.uva.dbbench.schema.fields.IntegerField;
import nl.jkoetsier.uva.dbbench.schema.fields.VarCharField;
import nl.jkoetsier.uva.dbbench.schema.visitor.SchemaVisitor;

public class SqlSchemaVisitor extends SchemaVisitor {

  private String output = "";
  private List<ColumnDef> columnDefStack = new ArrayList<>();

  private ColumnDef createColumnDef(Field field, String type) {
    ColumnDef columnDef = new ColumnDef(type, field.getName(), field.isAllowedEmpty());

    if (field.isAutoGenerated()) {
      columnDef.setAutoGenerated(true);
    }

    if (field.hasArguments()) {
      columnDef.setArguments(field.getArguments());
    }

    return columnDef;
  }

  public String getOutput() {
    return output;
  }

  @Override
  public void visit(BigIntegerField bigIntegerField) {
    columnDefStack.add(createColumnDef(bigIntegerField, "BIGINT"));
  }

  @Override
  public void visit(BooleanField booleanField) {
    columnDefStack.add(createColumnDef(booleanField, "BIT"));
  }

  @Override
  public void visit(CharField charField) {
    columnDefStack.add(createColumnDef(charField, "CHAR"));
  }

  @Override
  public void visit(DateField dateField) {
    columnDefStack.add(createColumnDef(dateField, "DATE"));
  }

  @Override
  public void visit(DateTimeField dateTimeField) {
    columnDefStack.add(createColumnDef(dateTimeField, "DATETIME"));
  }

  @Override
  public void visit(DateTimeTimezoneField dateTimeOffsetField) {
    columnDefStack.add(createColumnDef(dateTimeOffsetField, "DATETIMEOFFSET"));
  }

  @Override
  public void visit(DecimalField decimalField) {


    columnDefStack.add(createColumnDef(decimalField, "DECIMAL"));
  }

  @Override
  public void visit(DoubleField doubleField) {
    columnDefStack.add(createColumnDef(doubleField, "DOUBLE"));
  }

  @Override
  public void visit(FloatField floatField) {
    columnDefStack.add(createColumnDef(floatField, "FLOAT"));
  }

  @Override
  public void visit(IntegerField integerField) {
    columnDefStack.add(createColumnDef(integerField, "INT"));
  }

  @Override
  public void visit(VarCharField varCharField) {
    columnDefStack.add(createColumnDef(varCharField, "VARCHAR"));
  }

  @Override
  public void visit(Schema dataModel) {

  }

  @Override
  public void visit(Entity entity) {
     String createTable = String.format(
         "CREATE TABLE %s (", entity.getName()
     );

     for (ColumnDef columnDef : columnDefStack) {
       String arguments = "";

       if (columnDef.getArguments() != null) {
         arguments = String.format(
             "(%s)",
             String.join(",", columnDef.getArguments())
         );
       }

       createTable = createTable.concat(String.format(
           "\n\t%s %s%s%s %s,",
           columnDef.getName(),
           columnDef.getType(),
           arguments,
           columnDef.isAutoGenerated() ? " IDENTITY(1,1)" : "",
           columnDef.isNull() ? "NULL" : "NOT NULL"
       ));
     }

     if (entity.getPrimaryKey() != null) {
       List<String> keyFields = new ArrayList<>();

       for (Field field : entity.getPrimaryKey()) {
         keyFields.add(field.getName());
       }

       createTable = createTable.concat(String.format(
           "\n\tCONSTRAINT PK_%s PRIMARY KEY (\n\t\t%s\n\t)",
           entity.getName(),
           String.join(",\n\t\t", keyFields)
       ));


     } else if (createTable.charAt(createTable.length() - 1) == ',') {
       createTable = createTable.substring(0, createTable.length() - 1);
     }

     createTable = createTable.concat("\n);\n\n");

     output = output.concat(createTable);

     columnDefStack.clear();
  }
}
