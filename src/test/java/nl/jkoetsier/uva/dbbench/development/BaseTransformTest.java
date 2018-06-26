package nl.jkoetsier.uva.dbbench.development;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import nl.jkoetsier.uva.dbbench.connector.SqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.SqlQuery;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.util.FileReader;
import nl.jkoetsier.uva.dbbench.util.QueryFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTransformTest {

  private static Logger logger = LoggerFactory.getLogger(BaseTransformTest.class);

  private String inputFile = "dev/biqh_workload.sql";
  private int start = 0;
  private int count = 100;
  private boolean printInfo = false;

  @Test
  public abstract void testWorkload();

  protected abstract SqlWorkloadVisitor getWorkloadVisitor();

  private String[] getQueries(String filename) {
    String file = FileReader.readAsString(filename);

    return file.split(";");
  }

  /**
   * Strips queries for comparison between MSSQL and Db X SQL
   */
  private String stripQuery(String query) {
    String result = query;

    result = result.replaceAll("(\\s|\\[|]|\"|`)", "");
    result = result.replaceAll("(?i)(TOP\\(\\d+\\)|LIMIT\\d+)", "");
    result = result.replaceAll("(?i)rowsfetchnext\\d+rowsonly", "");
    result = result.toLowerCase();

    return stripQueryExtra(result);
  }

  // To be overriden by child class
  protected String stripQueryExtra(String query) {
    return query;
  }

  protected SqlQuery getOutputQuery(String inputQuery) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    Workload workload = workloadReader.fromString(inputQuery);

    Collection<Query> queries = workload.getQueries().values();

    assertEquals(1, queries.size());

    SqlWorkloadVisitor workloadVisitor = getWorkloadVisitor();
    Query query = queries.iterator().next();

    query.acceptVisitor(workloadVisitor);

    Collection<SqlQuery> queriesAsString = workloadVisitor.getResult();

    assertEquals(1, queriesAsString.size());

    return queriesAsString.iterator().next();
  }

  /*
   * Compares MySQL output with MsSQL output. Assumes MsSQL output is correct (see
   * MsSqlTransformTest). Omits TOP/LIMIT clauses. Need to be checked manually.
   */
  public void testWorkloadImpl() {
    String[] queries = getQueries(inputFile);
    MsSqlTransformTest msSqlTransformTest = new MsSqlTransformTest();

    for (int i = start; i < queries.length && i < start + count; i++) {
      String inputQuery = queries[i];
      String outputQuery = getOutputQuery(inputQuery).getQueryString();
      String msSqlOutput = msSqlTransformTest.getOutputQuery(inputQuery);

      if (printInfo) {
        printInfo(inputQuery, outputQuery, msSqlOutput, i);
      }

      assertEquals(stripQuery(msSqlOutput), stripQuery(outputQuery));

      // Check reflexivity
      String output2Query = getOutputQuery(outputQuery).getQueryString();
      assertEquals(output2Query, outputQuery);
    }
  }

  private void printInfo(String inputQuery, String outputQuery, String msSqlOutput, int i) {
    System.out.println("INPUT " + i + ": " + QueryFormatter.format(inputQuery));
    System.out.println();
    System.out.println("OUTPUT " + i + ": " + QueryFormatter.format(outputQuery));
    System.out.println("OUTPUT MsSQL " + i + ": " + QueryFormatter.format(msSqlOutput));
  }
}
