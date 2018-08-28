package nl.jkoetsier.terry.connector.mongodb.workload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.terry.connector.mongodb.MongoDbQuery;
import nl.jkoetsier.terry.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.terry.intrep.ExecutableQuery;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.connector.WorkloadTest;
import nl.jkoetsier.terry.util.TestDataHelper;
import org.junit.Test;

public class MongoDbWorkloadVisitorTest implements WorkloadTest {

  private TestDataHelper testDataHelper = new TestDataHelper();

  private Workload getWorkloadFromFile(String filename) {
    SqlWorkloadReader reader = new SqlWorkloadReader();
    return reader.fromFile(testDataHelper.getFilePath("sql/" + filename));
  }

  private List<ExecutableQuery> getGeneratedWorkload(String filename) {
    Workload workload = getWorkloadFromFile(filename);

    MongoDbWorkloadVisitor visitor = new MongoDbWorkloadVisitor();
    workload.acceptVisitor(visitor);
    return visitor.getResult();
  }

  @Test
  public void testSelectSimple() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_simple.sql");

    assertEquals(2, result.size());
    assertTrue(result.get(0) instanceof MongoDbQuery);

    MongoDbQuery executableQuery = (MongoDbQuery)result.get(0);
    assertEquals("table2name", executableQuery.getCollection());

    assertNull(executableQuery.getFind());
    assertNull(executableQuery.getProjection());

    assertTrue(result.get(1) instanceof  MongoDbQuery);
    MongoDbQuery executableQuery2 = (MongoDbQuery)result.get(1);
    assertEquals("table2name", executableQuery2.getCollection());
    assertNull(executableQuery2.getFind());

    BasicDBObject expectedProjection = new BasicDBObject("a", 1);
    expectedProjection.append("b", 1);

    assertEquals(expectedProjection, executableQuery2.getProjection());
  }

  @Override
  @Test
  public void testWhereNot() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_where.sql");

    assertEquals(1, result.size());
    assertTrue(result.get(0) instanceof MongoDbQuery);

    MongoDbQuery executableQuery = (MongoDbQuery)result.get(0);
    assertEquals("table2name", executableQuery.getCollection());


    List<BasicDBObject> list = new ArrayList<>();
    list.add(new BasicDBObject(
        "b", new BasicDBObject("$ne",
          new BasicDBObject("$numberLong", "3")
    )));
    list.add(new BasicDBObject(
        "$not", new BasicDBObject("a", 4.4)
    ));

    BasicDBObject expectedFind = new BasicDBObject("$and", list);

    BasicDBObject expectedProjection = new BasicDBObject("a", 1);

    assertEquals(expectedFind.toJson(), executableQuery.getFind().toJson());
    assertEquals(expectedProjection, executableQuery.getProjection());
  }

  @Override
  @Test
  public void testSimpleQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("output_sql_simple.sql");

    assertEquals(1, result.size());
    assertFalse(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testUnionQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_union_simple.sql");

    assertEquals(1, result.size());
    assertFalse(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testUnionAllQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_union_all_simple.sql");

    assertEquals(1, result.size());
    assertFalse(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testJoinSimpleQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_join_simple.sql");

    assertEquals(1, result.size());
    assertFalse(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testJoinMultipleQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_join_multiple.sql");

    assertEquals(1, result.size());
    assertFalse(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testTopQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_top.sql");

    assertEquals(2, result.size());

    assertTrue(result.get(0) instanceof MongoDbQuery);
    MongoDbQuery query1 = (MongoDbQuery)result.get(0);

    assertTrue(result.get(1) instanceof MongoDbQuery);
    MongoDbQuery query2 = (MongoDbQuery)result.get(1);

    assertNull(query1.getProjection());
    assertNull(query1.getFind());
    assertEquals(new Long(3), query1.getLimit());
    assertEquals("table2name", query1.getCollection());

    BasicDBObject expectedProj2 = new BasicDBObject("a", 1).append("b", 1);
    assertEquals(expectedProj2, query2.getProjection());

    assertEquals(expectedProj2, query2.getProjection());
    assertNull(query2.getFind());
    assertEquals("table2name", query2.getCollection());
    assertEquals(new Long(4), query2.getLimit());
    assertNull(query2.getSort());
    assertNull(query2.getOffset());
  }

  @Override
  @Test
  public void testLimitQuery() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_limit.sql");

    assertEquals(2, result.size());

    assertTrue(result.get(0) instanceof MongoDbQuery);
    MongoDbQuery query1 = (MongoDbQuery)result.get(0);

    assertTrue(result.get(1) instanceof MongoDbQuery);
    MongoDbQuery query2 = (MongoDbQuery)result.get(1);

    assertNull(query1.getProjection());
    assertNull(query1.getFind());
    assertEquals(new Long(1), query1.getLimit());
    assertEquals("table2name", query1.getCollection());

    BasicDBObject expectedProj2 = new BasicDBObject("a", 1).append("b", 1);
    assertEquals(expectedProj2, query2.getProjection());

    assertEquals(expectedProj2, query2.getProjection());
    assertNull(query2.getFind());
    assertEquals("table2name", query2.getCollection());
    assertEquals(new Long(2), query2.getLimit());
    assertNull(query2.getSort());
    assertNull(query2.getOffset());
  }

  @Override
  @Test
  public void testCase() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_case.sql");

    assertEquals(1, result.size());
    assertTrue(result.get(0).isSupported());
  }

  @Override
  @Test
  public void testDistinct() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_distinct.sql");


    // TODO. Aggregation
  }

  @Override
  @Test
  public void testWhereAndNotList() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_where_and_not_list.sql");

    assertEquals(1, result.size());

    assertTrue(result.get(0) instanceof MongoDbQuery);
    MongoDbQuery query = (MongoDbQuery)result.get(0);

    BasicDBObject expectedProjection = new BasicDBObject("a", 1);
    assertEquals(expectedProjection, query.getProjection());
    assertNull(query.getOffset());
    assertNull(query.getLimit());
    assertNull(query.getSort());
    assertEquals("table2name", query.getCollection());

    List<BasicDBObject> notList = new ArrayList<>();
    notList.add(new BasicDBObject("b", new BasicDBObject("$numberLong", "4")));
    notList.add(new BasicDBObject("b", new BasicDBObject("$gt", new BasicDBObject("$numberLong", "6"))));

    List<BasicDBObject> outerList = new ArrayList<>();
    outerList.add(new BasicDBObject("a", new BasicDBObject("$numberLong", "29")));
    outerList.add(new BasicDBObject("$not", new BasicDBObject("$and", notList)));
    BasicDBObject expectedFind = new BasicDBObject("$and", outerList);
    assertEquals(expectedFind.toJson(), query.getFind().toJson());
  }

  @Override
  @Test
  public void testAllFromTable() {
    List<ExecutableQuery> result = getGeneratedWorkload("select_all_from_table.sql");

    assertEquals(1, result.size());
    assertTrue(result.get(0) instanceof MongoDbQuery);

    MongoDbQuery query = (MongoDbQuery)result.get(0);
    assertEquals("table2name", query.getCollection());
    assertNull(query.getOffset());
    assertNull(query.getLimit());
    assertNull(query.getSort());
    assertNull(query.getFind());
    assertNull(query.getProjection());
  }
}
