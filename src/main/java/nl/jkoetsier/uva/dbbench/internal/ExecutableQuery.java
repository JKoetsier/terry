package nl.jkoetsier.uva.dbbench.internal;

import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExecutableQuery {

  private static Logger logger = LoggerFactory.getLogger(ExecutableQuery.class);

  private boolean supported = true;

  public boolean isSupported() {
    return supported;
  }

  public void setSupported(boolean supported) {
    this.supported = supported;
  }
}
