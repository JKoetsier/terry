package nl.jkoetsier.uva.dbbench.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlQuery extends ExecutableQuery {

  private static Logger logger = LoggerFactory.getLogger(SqlQuery.class);

  private String queryString;

  public SqlQuery(String queryString) {
    this.queryString = queryString;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }
}
