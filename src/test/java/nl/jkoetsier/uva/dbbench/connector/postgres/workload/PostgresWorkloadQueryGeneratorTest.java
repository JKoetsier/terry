package nl.jkoetsier.uva.dbbench.connector.postgres.workload;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.util.TestDataHelper;
import org.junit.Test;

public class PostgresWorkloadQueryGeneratorTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Workload getWorkloadFromFile(String filename) {
    SqlWorkloadReader reader = new SqlWorkloadReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private List<String> getGeneratedWorkload(String filename) {
    Workload workload = getWorkloadFromFile(filename);

    PostgresWorkloadQueryGenerator postgresWorkloadQueryGenerator = new PostgresWorkloadQueryGenerator();
    HashMap<Integer, String> result = postgresWorkloadQueryGenerator.generateQueries(workload);

    return new ArrayList<>(result.values());
  }


  private void compareSingleQueryFromFile(String filename, String expected) {
    List<String> result = getGeneratedWorkload(filename);

    assertEquals(1, result.size());
    assertEquals(expected, result.get(0));
  }

  private void compareMultipleQueriesFromFile(String filename, List<String> expected) {
    List<String> result = getGeneratedWorkload(filename);

    assertEquals(expected.size(), result.size());

    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), result.get(i));
    }
  }

  @Test
  public void generateSimpleQuery() {
    String expected = "SELECT t1.a AS a, tn2.b AS b FROM tableName AS t1 "
        + "LEFT OUTER JOIN tableName2 AS tn2 ON t1.c = tn2.c WHERE t1.a = 4";

    compareSingleQueryFromFile("output_sql_simple.sql", expected);
  }

  @Test
  public void testUnionQuery() {
    String expected = "SELECT a, b FROM basetable WHERE a = 4 UNION "
        + "SELECT c, d FROM jointable WHERE d = 5";

    compareSingleQueryFromFile("select_union_simple.sql", expected);
  }

  @Test
  public void testUnionAllQuery() {
    String expected = "SELECT a, b FROM basetable WHERE a = 4 UNION ALL "
        + "SELECT c, d FROM jointable WHERE d = 5";

    compareSingleQueryFromFile("select_union_all_simple.sql", expected);
  }

  @Test
  public void testJoinSimpleQuery() {
    String expected = "SELECT basetable.a, basetable.b, jointable.d FROM basetable "
        + "LEFT OUTER JOIN jointable ON basetable.b = jointable.c";

    compareSingleQueryFromFile("select_join_simple.sql", expected);
  }

  @Test
  public void testJoinMultipleQuery() {
    String expected = "SELECT basetable.a, basetable.b, jointable.d, jointable2.e, "
        + "jointable3.g "
        + "FROM basetable "
        + "LEFT OUTER JOIN jointable "
        + "ON basetable.b = jointable.c "
        + "RIGHT OUTER JOIN jointable2 "
        + "ON basetable.b = jointable2.e "
        + "INNER JOIN jointable3 "
        + "ON basetable.b = jointable3.f "
        + "FULL JOIN jointable4 "
        + "ON basetable.b = jointable4.h "
        + "WHERE basetable.b = 34";

    compareSingleQueryFromFile("select_join_multiple.sql", expected);
  }

  @Test
  public void testTopQuery() {
    List<String> expected = new ArrayList<>();
    expected.add("SELECT * FROM table2name LIMIT 3");
    expected.add("SELECT table2name.a, table2name.b FROM table2name LIMIT 4");

    compareMultipleQueriesFromFile("select_top.sql", expected);
  }

  @Test
  public void testLimitQuery() {
    List<String> expected = new ArrayList<>();
    expected.add("SELECT * FROM table2name LIMIT 1");
    expected.add("SELECT table2name.a, table2name.b FROM table2name LIMIT 2");

    compareMultipleQueriesFromFile("select_limit.sql", expected);
  }

  @Test
  public void testCase() {
    String expected = "SELECT a, b, CASE WHEN c IS NULL THEN CAST(NULL AS int) ELSE 1 END AS c"
        + " FROM tablename";

    compareSingleQueryFromFile("select_case.sql", expected);
  }

}