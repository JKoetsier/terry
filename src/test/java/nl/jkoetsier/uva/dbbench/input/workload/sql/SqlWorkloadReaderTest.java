package nl.jkoetsier.uva.dbbench.input.workload.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.schema.fields.IntegerColumn;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.RAJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlWorkloadReaderTest {

  private static Logger logger = LoggerFactory.getLogger(SqlWorkloadReaderTest.class);
  private Schema dataModel = new Schema();
  private TestDataHelper testDataHelper = new TestDataHelper();

  private Workload getWorkload(String filename) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    return workloadReader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private Workload getWorkloadFromString(String workload) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    return workloadReader.fromString(workload);
  }

  private void loadDataModel() {
    Table table = new Table("table2name");
    IntegerColumn fieldA = new IntegerColumn("a");
    IntegerColumn fieldB = new IntegerColumn("b");
    table.addColumn(fieldA);
    table.addColumn(fieldB);
    dataModel.addEntity(table);
  }

  private void loadJoinDataModel() {
    Table table = new Table("basetable");
    IntegerColumn fieldA = new IntegerColumn("a");
    IntegerColumn fieldB = new IntegerColumn("b");
    table.addColumn(fieldA);
    table.addColumn(fieldB);

    Table joinTable = new Table("jointable");
    IntegerColumn fieldC = new IntegerColumn("c");
    IntegerColumn fieldD = new IntegerColumn("d");
    joinTable.addColumn(fieldC);
    joinTable.addColumn(fieldD);

    Table joinTable2 = new Table("jointable2");
    Table joinTable3 = new Table("jointable3");
    Table joinTable4 = new Table("jointable4");
    IntegerColumn fieldE = new IntegerColumn("e");
    IntegerColumn fieldF = new IntegerColumn("f");
    IntegerColumn fieldG = new IntegerColumn("g");
    IntegerColumn fieldH = new IntegerColumn("h");
    joinTable2.addColumn(fieldE);
    joinTable3.addColumn(fieldF);
    joinTable3.addColumn(fieldG);
    joinTable4.addColumn(fieldH);
    dataModel.addEntity(table);
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

  private void validateWorkload(Workload workload, Schema schema) {
    try {
      workload.validate(schema);
    } catch (NotMatchingWorkloadException e) {
      e.printStackTrace();
      fail("Exception should not be thrown");
    }
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

    Query queryOne = workload.getQuery(0);
    assertTrue(queryOne.getRelation() instanceof Projection);

    Query queryTwo = workload.getQuery(1);
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
    } catch (InvalidQueryException e) {

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
    } catch (InvalidQueryException e) {

    }

    cleanup();
  }


  @Test
  public void testFieldWithoutTableprefix() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT a FROM table2name");

    assertEquals(1, workload.getQueries().size());
    Query query = workload.getQuery(0);
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
    } catch (InvalidQueryException e) {

    }

    cleanup();
  }

  @Test
  public void testSelectsWithWhere() {
    loadDataModel();
    Workload workload = getWorkload("select_where.sql");

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQuery(0);
    assertTrue(query.getRelation() instanceof Projection);
    assertTrue(((Projection) query.getRelation()).getInput() instanceof Selection);

    Selection selection = (Selection) ((Projection) query.getRelation()).getInput();

    assertNotNull(selection.getWhereExpression());
    assertTrue(selection.getWhereExpression() instanceof BinExpression);

    BinExpression binExpression = (BinExpression) selection.getWhereExpression();

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
  @Ignore("Validation deactivated/not working at the moment")
  public void testSelectWithInvalidExpression() {
    loadDataModel();
    Workload workload = getWorkloadFromString("SELECT table2name.b "
        + "FROM table2name WHERE table2name.c = 4");

    try {
      workload.validate(dataModel);
      fail("Exception not thrown");
    } catch (InvalidQueryException e) {

    }

    cleanup();
  }

  @Test
  public void testJoinSelect() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_simple.sql");

    validateWorkload(workload, dataModel);

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQuery(0);
    assertTrue(query.getRelation() instanceof Projection);

    Projection projection = (Projection) query.getRelation();
    assertEquals(3, projection.getSelectExpressions().size());

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
    assertNotNull(fieldExpression.getExposedField());

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
    } catch (InvalidQueryException e) {

    }

    cleanup();
  }

  @Test
  public void testMultipleJoins() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_join_multiple.sql");

    validateWorkload(workload, dataModel);

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQuery(0);
    assertTrue(query.getRelation() instanceof Projection);

    Projection projection = (Projection) query.getRelation();
    assertEquals(5, projection.getSelectExpressions().size());

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
  public void testUnionSimple() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_union_simple.sql");

    validateWorkload(workload, dataModel);

    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQuery(0);
    assertTrue(query.getRelation() instanceof Union);
    Union union = (Union) query.getRelation();
    assertFalse(union.isAll());

    assertTrue(union.getLeftInput() instanceof Projection);
    assertTrue(union.getRightInput() instanceof Projection);

    Projection leftInput = (Projection) union.getLeftInput();
    Projection rightInput = (Projection) union.getRightInput();

    assertTrue(leftInput.getInput() instanceof Selection);
    assertTrue(rightInput.getInput() instanceof Selection);

    assertEquals(2, leftInput.getSelectExpressions().size());
    assertTrue(leftInput.getSelectExpressions().get(0).getExpression() instanceof FieldExpression);
    assertTrue(leftInput.getSelectExpressions().get(1).getExpression() instanceof FieldExpression);

    FieldExpression fieldExpressionA = (FieldExpression) (leftInput.getSelectExpressions().get(0)
        .getExpression());
    FieldExpression fieldExpressionB = (FieldExpression) (leftInput.getSelectExpressions().get(1)
        .getExpression());
    assertEquals("basetable.a", fieldExpressionA.getFieldName());
    assertEquals("basetable.b", fieldExpressionB.getFieldName());

    Selection leftSelection = (Selection) leftInput.getInput();

    assertTrue(leftSelection.getWhereExpression() instanceof BinExpression);

    BinExpression binExpression = (BinExpression) leftSelection.getWhereExpression();
    assertTrue(binExpression.getLeftExpr() instanceof FieldExpression);
    assertTrue(binExpression.getRightExpr() instanceof LongConstant);
    assertTrue(binExpression.getOperator() instanceof EqualsOp);

    cleanup();
  }

  @Test
  public void testUnionAllSimple() {
    loadJoinDataModel();
    Workload workload = getWorkload("select_union_all_simple.sql");

    validateWorkload(workload, dataModel);
    assertEquals(1, workload.getQueries().size());

    Query query = workload.getQuery(0);
    assertTrue(query.getRelation() instanceof Union);
    Union union = (Union) query.getRelation();
    assertTrue(union.isAll());

    cleanup();
  }

  @Test
  public void testTop() {
    loadDataModel();
    Workload workload = getWorkload("select_limit.sql");
    validateWorkload(workload, dataModel);

    assertEquals(2, workload.getQueries().size());

    Query query1 = workload.getQuery(0);
    assertTrue(query1.getRelation() instanceof Projection);

    Projection projection1 = (Projection) query1.getRelation();
    assertTrue(projection1.getLimit() instanceof LongConstant);

    LongConstant longConstant1 = (LongConstant) projection1.getLimit();
    assertEquals(1, (long) longConstant1.getValue());

    Query query2 = workload.getQuery(1);
    assertTrue(query2.getRelation() instanceof Projection);

    Projection projection2 = (Projection) query2.getRelation();
    assertTrue(projection2.getLimit() instanceof LongConstant);

    LongConstant longConstant2 = (LongConstant) projection2.getLimit();
    assertEquals(2, (long) longConstant2.getValue());

    cleanup();
  }

  @Test
  public void testLimit() {
    loadDataModel();
    Workload workload = getWorkload("select_top.sql");
    validateWorkload(workload, dataModel);

    assertEquals(2, workload.getQueries().size());

    Query query1 = workload.getQuery(0);
    assertTrue(query1.getRelation() instanceof Projection);

    Projection projection1 = (Projection) query1.getRelation();
    assertTrue(projection1.getLimit() instanceof LongConstant);

    LongConstant longConstant1 = (LongConstant) projection1.getLimit();
    assertEquals(3, (long) longConstant1.getValue());

    Query query2 = workload.getQuery(1);
    assertTrue(query2.getRelation() instanceof Projection);

    Projection projection2 = (Projection) query2.getRelation();
    assertTrue(projection2.getLimit() instanceof LongConstant);

    LongConstant longConstant2 = (LongConstant) projection2.getLimit();
    assertEquals(4, (long) longConstant2.getValue());

    cleanup();
  }

  @Test
  public void testDistinct() {
    loadDataModel();
    Workload workload = getWorkload("select_distinct.sql");
    validateWorkload(workload, dataModel);

    assertEquals(2, workload.getQueries().size());

    Query query1 = workload.getQuery(0);
    Query query2 = workload.getQuery(1);

    assertTrue(query1.getRelation() instanceof Projection);
    assertTrue(query2.getRelation() instanceof Projection);

    Projection projection1 = (Projection) query1.getRelation();
    Projection projection2 = (Projection) query2.getRelation();

    assertTrue(projection1.isDistinct());
    assertFalse(projection2.isDistinct());
  }
}