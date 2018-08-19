package nl.jkoetsier.terry.intrep.schema.visitor;

import java.util.HashMap;
import nl.jkoetsier.terry.intrep.SqlQuery;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.schema.Table;
import nl.jkoetsier.terry.intrep.schema.column.BigIntegerColumn;
import nl.jkoetsier.terry.intrep.schema.column.BooleanColumn;
import nl.jkoetsier.terry.intrep.schema.column.CharColumn;
import nl.jkoetsier.terry.intrep.schema.column.DateColumn;
import nl.jkoetsier.terry.intrep.schema.column.DateTimeColumn;
import nl.jkoetsier.terry.intrep.schema.column.DateTimeTimezoneColumn;
import nl.jkoetsier.terry.intrep.schema.column.DecimalColumn;
import nl.jkoetsier.terry.intrep.schema.column.DoubleColumn;
import nl.jkoetsier.terry.intrep.schema.column.FloatColumn;
import nl.jkoetsier.terry.intrep.schema.column.IntegerColumn;
import nl.jkoetsier.terry.intrep.schema.column.VarCharColumn;

public abstract class SchemaVisitor {

  public abstract void visit(BigIntegerColumn bigIntegerColumn);

  public abstract void visit(BooleanColumn booleanColumn);

  public abstract void visit(CharColumn charColumn);

  public abstract void visit(DateColumn dateColumn);

  public abstract void visit(DateTimeColumn dateTimeColumn);

  public abstract void visit(DateTimeTimezoneColumn dateTimeOffsetField);

  public abstract void visit(DecimalColumn decimalColumn);

  public abstract void visit(DoubleColumn doubleColumn);

  public abstract void visit(FloatColumn floatField);

  public abstract void visit(IntegerColumn integerField);

  public abstract void visit(VarCharColumn varCharField);

  public abstract void visit(Schema dataModel);

  public abstract void visit(Table table);

  /**
   * @return Returns a Hashmap of <EntityName, QueryString> pairs
   */
  public abstract HashMap<String, SqlQuery> getCreateQueries();
}
