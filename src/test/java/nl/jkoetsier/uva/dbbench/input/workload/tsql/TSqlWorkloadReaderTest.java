package nl.jkoetsier.uva.dbbench.input.workload.tsql;

import nl.jkoetsier.uva.dbbench.workload.Query;
import nl.jkoetsier.uva.dbbench.workload.Workload;
import nl.jkoetsier.uva.dbbench.workload.relalg.Project;
import nl.jkoetsier.uva.dbbench.workload.relalg.Select;
import org.junit.Test;

import static org.junit.Assert.*;

public class TSqlWorkloadReaderTest {
    String dataDirectory = "/data/tsql/";

    private String getFilepath(String filename) {
        return getClass().getResource(dataDirectory + filename).getFile();
    }

    private Workload getWorkload(String filename) {
        TSqlWorkloadReader workloadReader = new TSqlWorkloadReader();
        return workloadReader.fromFile(getFilepath(filename));
    }

    @Test
    public void testSelect() {
        Workload workload = getWorkload("select_simple.sql");
        assertEquals(workload.getQueries().size(), 2);

        Query queryOne = workload.getQueries().get(0);
        Query queryTwo = workload.getQueries().get(1);

        assertTrue(queryOne.getRelation() instanceof Select);
        assertTrue(queryTwo.getRelation() instanceof Project);
        assertTrue(((Project)queryTwo.getRelation()).getInput() instanceof Select);
    }
}