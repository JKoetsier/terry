package nl.jkoetsier.uva.dbbench.input.yaml;

import java.util.ArrayList;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlQuery {

  private static Logger logger = LoggerFactory.getLogger(YamlQuery.class);

  private String identifier;
  private List<String> results;
  private String query;

  public YamlQuery() {
    results = new ArrayList<>();
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public List<String> getResults() {
    return results;
  }

  public void setResults(List<String> results) {
    this.results = results;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }
}
