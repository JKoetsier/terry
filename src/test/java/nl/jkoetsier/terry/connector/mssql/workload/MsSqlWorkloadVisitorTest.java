package nl.jkoetsier.terry.connector.mssql.workload;

import static nl.jkoetsier.terry.util.Assertions.assertQueryEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.terry.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.terry.intrep.SqlQuery;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.connector.WorkloadTest;
import nl.jkoetsier.terry.util.TestDataHelper;
import org.junit.Test;

public class MsSqlWorkloadVisitorTest implements WorkloadTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Workload getWorkloadFromFile(String filename) {
    SqlWorkloadReader reader = new SqlWorkloadReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private List<SqlQuery> getGeneratedWorkload(String filename) {
    Workload workload = getWorkloadFromFile(filename);

    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    workload.acceptVisitor(workloadVisitor);

    return workloadVisitor.getResult();
  }


  private void compareSingleQueryFromFile(String filename, String expected) {
    List<SqlQuery> result = getGeneratedWorkload(filename);

    assertEquals(1, result.size());
    assertQueryEquals(expected, result.get(0).getQueryString());
  }

  private void compareMultipleQueriesFromFile(String filename, List<String> expected) {
    List<SqlQuery> result = getGeneratedWorkload(filename);

    assertEquals(expected.size(), result.size());

    for (int i = 0; i < expected.size(); i++) {
      assertQueryEquals(expected.get(i), result.get(i).getQueryString());
    }
  }

  @Test
  public void testSimpleQuery() {
    String expected = "SELECT [t1].[a] AS [a], [tn2].[b] AS [b] FROM [tableName] AS [t1] "
        + "LEFT OUTER JOIN [tableName2] AS [tn2] ON ([t1].[c] = [tn2].[c]) WHERE ([t1].[a] = 4)";

    compareSingleQueryFromFile("output_sql_simple.sql", expected);
  }

  @Test
  public void testUnionQuery() {
    String expected = "SELECT [a], [b] FROM [basetable] WHERE ([a] = 4) UNION "
        + "(SELECT [c], [d] FROM [jointable] WHERE ([d] = 5))";

    compareSingleQueryFromFile("select_union_simple.sql", expected);
  }

  @Test
  public void testUnionAllQuery() {
    String expected = "SELECT [a], [b] FROM [basetable] WHERE ([a] = 4) UNION ALL "
        + "(SELECT [c], [d] FROM [jointable] WHERE ([d] = 5))";

    compareSingleQueryFromFile("select_union_all_simple.sql", expected);
  }

  @Test
  public void testJoinSimpleQuery() {
    String expected = "SELECT [basetable].[a], [basetable].[b], [jointable].[d] FROM [basetable] "
        + "LEFT OUTER JOIN [jointable] ON ([basetable].[b] = [jointable].[c])";

    compareSingleQueryFromFile("select_join_simple.sql", expected);
  }

  @Test
  public void testJoinMultipleQuery() {
    String expected = "SELECT [basetable].[a], [basetable].[b], [jointable].[d], [jointable2].[e], "
        + "[jointable3].[g] "
        + "FROM [basetable] "
        + "LEFT OUTER JOIN [jointable] "
        + "ON ([basetable].[b] = [jointable].[c]) "
        + "RIGHT OUTER JOIN [jointable2] "
        + "ON ([basetable].[b] = [jointable2].[e]) "
        + "INNER JOIN [jointable3] "
        + "ON ([basetable].[b] = [jointable3].[f]) "
        + "FULL JOIN [jointable4] "
        + "ON ([basetable].[b] = [jointable4].[h]) "
        + "WHERE ([basetable].[b] = 34)";

    compareSingleQueryFromFile("select_join_multiple.sql", expected);
  }

  @Test
  public void testTopQuery() {
    List<String> expected = new ArrayList<>();
    expected.add("SELECT TOP(3) * FROM [table2name]");
    expected.add("SELECT TOP(4) [table2name].[a], [table2name].[b] FROM [table2name]");

    compareMultipleQueriesFromFile("select_top.sql", expected);
  }

  @Test
  public void testLimitQuery() {
    List<String> expected = new ArrayList<>();
    expected.add("SELECT TOP(1) * FROM [table2name]");
    expected.add("SELECT TOP(2) [table2name].[a], [table2name].[b] FROM [table2name]");

    compareMultipleQueriesFromFile("select_limit.sql", expected);
  }

  @Test
  public void testCase() {
    String expected =
        "SELECT [a], [b], CASE WHEN [c] IS NULL THEN CAST(NULL AS int) ELSE 1 END AS [c]"
            + " FROM [tablename]";

    compareSingleQueryFromFile("select_case.sql", expected);
  }

  @Override
  @Test
  public void testDistinct() {
    List<String> expected = new ArrayList<>();
    expected.add("SELECT DISTINCT [table2name].[a] FROM [table2name]");
    expected.add("SELECT [table2name].[a] FROM [table2name]");

    compareMultipleQueriesFromFile("select_distinct.sql", expected);
  }

  @Test
  public void testWhereNot() {
    String expected = "SELECT [table2name].[a] FROM [table2name] WHERE (([b] != 3) "
        + "AND NOT ([table2name].[a] = 4.4))";

    compareSingleQueryFromFile("select_where.sql", expected);
  }

  @Test
  public void testWhereAndNotList() {
    String expected = "SELECT [a] FROM [table2name] WHERE (([a] = 29) AND NOT (([b] = 4) AND ([b] > 6)))";

    compareSingleQueryFromFile("select_where_and_not_list.sql", expected);
  }

  @Test
  public void testAllFromTable() {
    String expected = "SELECT [table2name].* FROM [table2name]";

    compareSingleQueryFromFile("select_all_from_table.sql", expected);
  }
}
