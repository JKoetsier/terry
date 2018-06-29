package nl.jkoetsier.uva.dbbench.connector;

import org.junit.Test;

public interface WorkloadTest {

  @Test
  void testSimpleQuery();

  @Test
  void testUnionQuery();

  @Test
  void testUnionAllQuery();

  @Test
  void testJoinSimpleQuery();

  @Test
  void testJoinMultipleQuery();

  @Test
  void testTopQuery();

  @Test
  void testLimitQuery();

  @Test
  void testCase();

  @Test
  void testDistinct();

  @Test
  void testWhereNot();

  @Test
  void testWhereAndNotList();

  @Test
  void testAllFromTable();


  // TODO IMPLEMENT
//  @Test
//  void testSelectSimpleJoin();
}
