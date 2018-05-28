package nl.jkoetsier.uva.dbbench.development;

import static org.junit.Assert.*;

import java.util.Collection;
import nl.jkoetsier.uva.dbbench.connector.mssql.workload.MsSqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.testclass.DevelopmentTest;
import nl.jkoetsier.uva.dbbench.util.FileReader;
import nl.jkoetsier.uva.dbbench.util.QueryFormatter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MsSqlTransformTest.class);

  private String inputFile = "dev/biqh_workload.sql";
  private int start = 0;
  private int count = 100;
  private boolean printInfo = false;

  private String[] getQueries(String filename) {
    String file = FileReader.readAsString(filename);

    return file.split(";");
  }

  String getOutputQuery(String inputQuery) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    Workload workload = workloadReader.fromString(inputQuery);

    Collection<Query> queries = workload.getQueries().values();

    assertEquals(1, queries.size());

    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    Query query = queries.iterator().next();

    query.acceptVisitor(workloadVisitor);

    Collection<String> queriesAsString = workloadVisitor.getResult().values();

    assertEquals(1, queriesAsString.size());

    return queriesAsString.iterator().next();
  }

  private String stripQuery(String query) {
    String result = query;
    result = result.replaceAll("dbo", "");
    result = result.replaceAll("ASC", "");
    result = result.replaceAll("(?i)outer join", "join");
    result = result.replaceAll("(?i)inner join", "join");
    result = result.replaceAll("(?i) AS ", "");
    result = result.replaceAll("\\W", "");
    result = result.toLowerCase();

    return result;
  }

  @Test
  @Category(DevelopmentTest.class)
  public void testWorkload() {
    String[] queries = getQueries(inputFile);

    for (int i = start; i < queries.length && i < start + count; i++) {
      String inputQuery = queries[i];
      String outputQuery = getOutputQuery(inputQuery);

      if (printInfo) {
        printInfo(inputQuery, outputQuery, i);
      }

      assertEquals(stripQuery(inputQuery), stripQuery(outputQuery));

      // Check reflexivity
      String output2Query = getOutputQuery(outputQuery);
      assertEquals(output2Query, outputQuery);
    }
  }

  private void printInfo(String inputQuery, String outputQuery, int i) {
    System.out.println("INPUTQUERY RAW: " + inputQuery);
    System.out.println("INPUT " + i + ": " + QueryFormatter.format(inputQuery));
    System.out.println();
    System.out.println("OUTPUT " + i + ": " + QueryFormatter.format(outputQuery));
    System.out.println("==========================================================");
  }
}
