package nl.jkoetsier.uva.dbbench.output.mssql.workload;

import static org.junit.Assert.*;

import java.util.List;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Test;

public class MsSqlWorkloadQueryGeneratorTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Workload getWorkloadFromFile(String filename) {
    SqlWorkloadReader reader = new SqlWorkloadReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private List<String> getGeneratedWorkload(String filename) {
    Workload workload = getWorkloadFromFile(filename);

    MsSqlWorkloadQueryGenerator msSqlWorkloadQueryGenerator = new MsSqlWorkloadQueryGenerator();
    return msSqlWorkloadQueryGenerator.generateQueries(workload);
  }

  @Test
  public void generateSimpleQuery() {
    List<String> result = getGeneratedWorkload("output_sql_simple.sql");

    assertEquals(1, result.size());

    String expected = "SELECT t1.a AS a, tn2.b AS b FROM tableName AS t1 LEFT OUTER JOIN "
        + "tableName2 AS tn2 ON t1.c = tn2.c WHERE t1.a = 4";

    assertEquals(expected, result.get(0));
  }

  @Test
  public void testUnionQuery() {
    List<String> result = getGeneratedWorkload("select_union_simple.sql");
    assertEquals(1, result.size());

    String expected = "SELECT a, b FROM basetable WHERE a = 4 UNION "
        + "SELECT c, d FROM jointable WHERE d = 5";

    assertEquals(expected, result.get(0));
  }

  @Test
  public void testUnionAllQuery() {
    List<String> result = getGeneratedWorkload("select_union_all_simple.sql");
    assertEquals(1, result.size());

    String expected = "SELECT a, b FROM basetable WHERE a = 4 UNION ALL "
        + "SELECT c, d FROM jointable WHERE d = 5";

    assertEquals(expected, result.get(0));
  }
}