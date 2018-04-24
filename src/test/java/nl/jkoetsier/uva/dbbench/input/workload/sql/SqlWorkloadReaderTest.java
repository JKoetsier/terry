package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.util.logging.Logger;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.output.mssql.MsSqlDatabaseInterface;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.IntegerField;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.internal.workload.query.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class SqlWorkloadReaderTest {

  String dataDirectory = "/data/sql/";
  Schema dataModel = Schema.getInstance();

  private String getFilepath(String filename) {
    return getClass().getResource(dataDirectory + filename).getFile();
  }

  private Workload getWorkload(String filename) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    return workloadReader.fromFile(getFilepath(filename));
  }

  private Workload getWorkloadFromString(String workload) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    return workloadReader.fromString(workload);
  }

  private void loadDataModel() {
    Entity entity = new Entity("table2name");
    IntegerField fieldA = new IntegerField("a");
    IntegerField fieldB = new IntegerField("b");
    entity.addField(fieldA);
    entity.addField(fieldB);
    dataModel.addEntity(entity);
  }

  private void loadJoinDataModel() {
    Entity entity = new Entity("basetable");
    IntegerField fieldA = new IntegerField("a");
    IntegerField fieldB = new IntegerField("b");
    entity.addField(fieldA);
    entity.addField(fieldB);

    Entity joinTable = new Entity("jointable");
    IntegerField fieldC = new IntegerField("c");
    IntegerField fieldD = new IntegerField("d");
    joinTable.addField(fieldC);
    joinTable.addField(fieldD);

    Entity joinTable2 = new Entity("jointable2");
    Entity joinTable3 = new Entity("jointable3");
    Entity joinTable4 = new Entity("jointable4");
    IntegerField fieldE = new IntegerField("e");
    IntegerField fieldF = new IntegerField("f");
    IntegerField fieldG = new IntegerField("g");
    IntegerField fieldH = new IntegerField("h");
    joinTable2.addField(fieldE);
    joinTable3.addField(fieldF);
    joinTable3.addField(fieldG);
    joinTable4.addField(fieldH);
    dataModel.addEntity(entity);
    dataModel.addEntity(joinTable);
    dataModel.addEntity(joinTable2);
    dataModel.addEntity(joinTable3);
    dataModel.addEntity(joinTable4);
  }

  private void cleanup() {
    clearDataModel();
  }

  private void clearDataModel() {
    dataModel.setEntities(new HashMap<>());
  }

  @Test
  public void testSimpleSelectWithoutMatchingDataModel() {
    Workload workload = getWorkload("select_simple.sql");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }

  }

  @Test
  public void testSimpleSelect() {
    loadDataModel();

    Workload workload = getWorkload("select_simple.sql");
    assertEquals(workload.getQueries().size(), 2);

    Query queryOne = workload.getQueries().get(0);
    assertTrue(queryOne.getRelation() instanceof Selection);

    Query queryTwo = workload.getQueries().get(1);
    assertTrue(queryTwo.getRelation() instanceof Projection);
    assertTrue(((Projection) queryTwo.getRelation()).getInput() instanceof Selection);

    cleanup();
  }

  @Test
  public void testNonExistingTableSelect() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT notexisting.a FROM table2name");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }
    cleanup();
  }

  @Test
  public void testNonExistingTableFieldSelect() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT table2name.c FROM table2name");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }

    cleanup();
  }


  @Test
  public void testFieldWithoutTableprefix() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT a FROM table2name");

    assertEquals(1, workload.getQueries().size());
    Query query = workload.getQueries().get(0);
    assertTrue(query.getRelation() instanceof Projection);
    assertTrue(((Projection) query.getRelation()).getInput() instanceof Selection);

    cleanup();
  }

  @Test
  public void testNonExistingTableInFromSelect() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT a FROM notexistingtable");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }
    cleanup();
  }

  @Test
  public void testNonExistingSingleFieldInExistingTableSelect() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT c FROM table2name");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }

    cleanup();
  }

  @Test
  public void testSelectsWithWhere() {
    loadDataModel();
    Workload workload = getWorkload("select_where.sql");

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQueries().get(0);
    assertTrue(query.getRelation() instanceof Projection);
    assertTrue(((Projection) query.getRelation()).getInput() instanceof Selection);

    Selection selection = (Selection) ((Projection) query.getRelation()).getInput();

    assertNotNull(selection.getExpression());
    assertTrue(selection.getExpression() instanceof BinExpression);

    BinExpression binExpression = (BinExpression) selection.getExpression();

    assertTrue(binExpression.getLeftExpr() instanceof BinExpression);
    assertTrue(binExpression.getRightExpr() instanceof BinExpression);
    assertTrue(binExpression.getOperator() instanceof AndOp);

    BinExpression leftExpr = (BinExpression) binExpression.getLeftExpr();
    BinExpression rightExpr = (BinExpression) binExpression.getRightExpr();

    assertTrue(leftExpr.getOperator() instanceof NeqOp);
    assertTrue(rightExpr.getOperator() instanceof EqualsOp);
    assertFalse(leftExpr.isNot());
    assertTrue(rightExpr.isNot());

    assertTrue(leftExpr.getLeftExpr() instanceof FieldExpression);
    assertTrue(leftExpr.getRightExpr() instanceof LongConstant);

    assertTrue(rightExpr.getLeftExpr() instanceof FieldExpression);
    assertTrue(rightExpr.getRightExpr() instanceof DoubleConstant);

    cleanup();
  }

  @Test
  public void testSelectWithInvalidExpression() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT table2name.b FROM table2name WHERE table2name.c = 4");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }

    cleanup();
  }

  @Test
  public void testJoinSelect() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_simple.sql");

    try {
      workload.validate(dataModel);
    } catch (NotMatchingWorkloadException e) {
      fail("Exception should not be thrown");
    }

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQueries().get(0);
    assertTrue(query.getRelation() instanceof Projection);

    Projection projection = (Projection) query.getRelation();
    assertEquals(3, projection.getFieldRefs().size());

    assertTrue(projection.getInput() instanceof Selection);
    Selection selection = (Selection) projection.getInput();

    assertTrue(selection.getInput() instanceof RAJoin);
    RAJoin raJoin = (RAJoin) selection.getInput();
    assertNotNull(raJoin.getOnExpression());

    assertTrue(raJoin.getLeftInput() instanceof InputRelation);
    assertTrue(raJoin.getRightInput() instanceof InputRelation);

    assertTrue(raJoin.getOnExpression() instanceof BinExpression);

    BinExpression binExpression = (BinExpression) raJoin.getOnExpression();
    assertTrue(binExpression.getOperator() instanceof EqualsOp);
    assertTrue(binExpression.getLeftExpr() instanceof FieldExpression);

    FieldExpression fieldExpression = (FieldExpression) binExpression.getLeftExpr();
    assertNotNull(fieldExpression.getFieldRef());

    cleanup();
  }

  @Test
  public void testJoinInvalidTableSelect() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_invalid_table.sql");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }
    cleanup();
  }

  @Test
  public void testJoinInvalidColumnSelect() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_invalid_column.sql");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (NotMatchingWorkloadException e) {

    }

    cleanup();
  }

  @Test
  public void testMultipleJoins() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_multiple.sql");

    try {
      workload.validate(dataModel);
    } catch (NotMatchingWorkloadException e) {
      fail("Exception should not be thrown");
    }

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQueries().get(0);
    assertTrue(query.getRelation() instanceof Projection);

    Projection projection = (Projection) query.getRelation();
    assertEquals(5, projection.getFieldRefs().size());

    assertTrue(projection.getInput() instanceof Selection);

    Selection selection = (Selection) projection.getInput();

    assertTrue(selection.getInput() instanceof FullJoin);
    FullJoin fullJoin = (FullJoin) selection.getInput();
    assertTrue(fullJoin.getLeftInput() instanceof InnerJoin);

    InnerJoin innerJoin = (InnerJoin) fullJoin.getLeftInput();
    assertTrue(innerJoin.getLeftInput() instanceof OuterJoin);

    OuterJoin outerJoin = (OuterJoin) innerJoin.getLeftInput();
    assertEquals(OuterJoin.Direction.RIGHT, outerJoin.getDirection());
    assertTrue(outerJoin.getLeftInput() instanceof OuterJoin);

    OuterJoin rightOuterJoin = (OuterJoin) outerJoin.getLeftInput();
    assertTrue(rightOuterJoin.getLeftInput() instanceof InputRelation);
    assertTrue(rightOuterJoin.getRightInput() instanceof InputRelation);
    assertEquals(OuterJoin.Direction.LEFT, rightOuterJoin.getDirection());

    cleanup();
  }


  @Test
  @Ignore
  public void testTestFile() {
    Mockito.mock(Logger.class);

    SqlSchemaReader schemaReader = new SqlSchemaReader();
    schemaReader.fromFile("../clean.sql");

    MsSqlDatabaseInterface msSqlRunner = new MsSqlDatabaseInterface();
    //msSqlRunner.setupDatabase();
  }
}