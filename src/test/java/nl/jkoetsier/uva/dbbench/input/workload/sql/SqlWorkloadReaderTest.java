package nl.jkoetsier.uva.dbbench.input.workload.sql;

import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.IntegerField;
import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.Workload;
import nl.jkoetsier.uva.dbbench.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.workload.query.Selection;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SqlWorkloadReaderTest {
    String dataDirectory = "/data/sql/";
    DataModel dataModel = DataModel.getInstance();

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

    private void cleanup() {
        clearDataModel();
    }

    private void clearDataModel() {
        dataModel.setEntities(new HashMap<>());
    }

    @Test(expected = InvalidQueryException.class)
    public void testSimpleSelectWithoutMatchingDataModel() {
        Workload workload = getWorkload("select_simple.sql");
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

    @Test(expected = InvalidQueryException.class)
    public void testNonExistingTableSelect() {
        loadDataModel();
        Workload workload = getWorkloadFromString("SELECT notexisting.a FROM table2name");
        cleanup();
    }

    @Test(expected = InvalidQueryException.class)
    public void testNonExistingTableFieldSelect() {
        loadDataModel();
        Workload workload = getWorkloadFromString("SELECT table2name.c FROM table2name");
        cleanup();
    }


    @Test
    public void testFieldWithoutTableprefix() {
        loadDataModel();
        Workload workload = getWorkloadFromString("SELECT a FROM table2name");

        assertEquals(workload.getQueries().size(), 1);
        Query query = workload.getQueries().get(0);
        assertTrue(query.getRelation() instanceof Projection);
        assertTrue(((Projection) query.getRelation()).getInput() instanceof Selection);

        cleanup();
    }

    @Test(expected = InvalidQueryException.class)
    public void testNonExistingTableInFromSelect() {
        loadDataModel();
        Workload workload = getWorkloadFromString("SELECT a FROM notexistingtable");
        cleanup();
    }

    @Test(expected = InvalidQueryException.class)
    public void testNonExistingSingleFieldInExistingTableSelect() {
        loadDataModel();
        Workload workload = getWorkloadFromString("SELECT c FROM table2name");
        cleanup();
    }

    @Test
    public void testSelectsWithWhere() {
        loadDataModel();
        Workload workload = getWorkload("select_where.sql");

        assertEquals(workload.getQueries().size(), 1);

        Query query = workload.getQueries().get(0);
        assertTrue(query.getRelation() instanceof Projection);
        assertTrue(((Projection)query.getRelation()).getInput() instanceof Selection);

        Selection selection = (Selection)((Projection)query.getRelation()).getInput();

        assertNotNull(selection.getExpression());
        assertTrue(selection.getExpression() instanceof BinExpression);

        BinExpression binExpression = (BinExpression)selection.getExpression();

        assertTrue(binExpression.getLeftExpr() instanceof BinExpression);
        assertTrue(binExpression.getRightExpr() instanceof BinExpression);
        assertTrue(binExpression.getOperator() instanceof AndOp);

        BinExpression leftExpr = (BinExpression)binExpression.getLeftExpr();
        BinExpression rightExpr = (BinExpression)binExpression.getRightExpr();

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

    @Test(expected = InvalidQueryException.class)
    public void testSelectWithInvalidExpression() {
        loadDataModel();
        getWorkloadFromString("SELECT table2name.b FROM table2name WHERE table2name.c = 4");
        cleanup();
    }
}