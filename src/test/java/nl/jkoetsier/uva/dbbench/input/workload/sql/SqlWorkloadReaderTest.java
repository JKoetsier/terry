package nl.jkoetsier.uva.dbbench.input.workload.sql;

import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.IntegerField;
import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.Workload;
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

    private void loadDataModel() {
        Entity entity = new Entity("table2name");
        IntegerField fieldA = new IntegerField("a");
        IntegerField fieldB = new IntegerField("b");
        entity.addField(fieldA);
        entity.addField(fieldB);
        dataModel.addEntity(entity);
    }

    private void clearDataModel() {
        dataModel.setEntities(new HashMap<>());
    }

    @Test(expected=InvalidQueryException.class)
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
        assertTrue(((Projection)queryTwo.getRelation()).getInput() instanceof  Selection);

        clearDataModel();
    }
}