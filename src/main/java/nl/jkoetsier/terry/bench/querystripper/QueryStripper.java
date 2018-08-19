package nl.jkoetsier.terry.bench.querystripper;

import java.util.List;
import nl.jkoetsier.terry.intrep.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryStripper {

  private static Logger logger = LoggerFactory.getLogger(QueryStripper.class);

  public static List<Query> stripQuery(Query query) {
    QueryStripVisitor visitor = new QueryStripVisitor();
    query.acceptVisitor(visitor);

    return visitor.getQueries();
  }
}
