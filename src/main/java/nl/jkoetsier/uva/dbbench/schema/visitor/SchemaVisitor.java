package nl.jkoetsier.uva.dbbench.schema.visitor;

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
import nl.jkoetsier.uva.dbbench.schema.fields.FloatField;
import nl.jkoetsier.uva.dbbench.schema.fields.IntegerField;
import nl.jkoetsier.uva.dbbench.schema.fields.VarCharField;

public abstract class SchemaVisitor {

  public abstract void visit(BigIntegerField bigIntegerField);
  public abstract void visit(BooleanField booleanField);
  public abstract void visit(CharField charField);
  public abstract void visit(DateField dateField);
  public abstract void visit(DateTimeField dateTimeField);
  public abstract void visit(DateTimeTimezoneField dateTimeOffsetField);
  public abstract void visit(DecimalField decimalField);
  public abstract void visit(DoubleField doubleField);
  public abstract void visit(FloatField floatField);
  public abstract void visit(IntegerField integerField);
  public abstract void visit(VarCharField varCharField);

  public abstract void visit(Schema dataModel);
  public abstract void visit(Entity entity);
}
