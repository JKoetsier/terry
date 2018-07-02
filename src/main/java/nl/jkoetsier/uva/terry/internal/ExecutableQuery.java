package nl.jkoetsier.uva.terry.internal;

import nl.jkoetsier.uva.terry.internal.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExecutableQuery implements Comparable<ExecutableQuery> {

  private static Logger logger = LoggerFactory.getLogger(ExecutableQuery.class);

  private boolean supported = true;
  private Query queryObject;

  public ExecutableQuery() {

  }

  public ExecutableQuery(Query queryObject) {
    this.queryObject = queryObject;
  }

  public String getIdentifier() {
    return queryObject.getIdentifier();
  }

  public boolean isSupported() {
    return supported;
  }

  public void setSupported(boolean supported) {
    this.supported = supported;
  }

  public Query getQueryObject() {
    return queryObject;
  }

  public void setQueryObject(Query queryObject) {
    this.queryObject = queryObject;
  }

  @Override
  public int compareTo(ExecutableQuery o) {
    return getIdentifier().compareTo(o.getIdentifier());
  }
}
