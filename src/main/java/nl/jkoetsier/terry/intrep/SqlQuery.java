package nl.jkoetsier.terry.intrep;

import nl.jkoetsier.terry.intrep.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlQuery extends ExecutableQuery {

  private static Logger logger = LoggerFactory.getLogger(SqlQuery.class);

  private String queryString;

  public SqlQuery(Query queryObject) {
    super(queryObject);
  }

  public SqlQuery(String queryString) {
    this.queryString = queryString;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  @Override
  public String toString() {
    return queryString;
  }
}
