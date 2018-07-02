package nl.jkoetsier.uva.terry.internal.schema.visitor;

import java.util.HashMap;
import nl.jkoetsier.uva.terry.internal.SqlQuery;
import nl.jkoetsier.uva.terry.internal.schema.Schema;
import nl.jkoetsier.uva.terry.internal.schema.Table;
import nl.jkoetsier.uva.terry.internal.schema.fields.BigIntegerColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.BooleanColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.CharColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DateColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DateTimeColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DateTimeTimezoneColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DecimalColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.DoubleColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.FloatColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.IntegerColumn;
import nl.jkoetsier.uva.terry.internal.schema.fields.VarCharColumn;

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
