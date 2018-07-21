package nl.jkoetsier.uva.terry.bench.results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import nl.jkoetsier.uva.terry.intrep.ExecutableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Results {

  private static Logger logger = LoggerFactory.getLogger(Results.class);

  private TreeMap<ExecutableQuery, RunResultList> results;
  private List<ExecutableQuery> queries;

  public Results(List<ExecutableQuery> queries) {
    this.queries = queries;
    Collections.sort(this.queries);

    initMap();
  }

  private void initMap() {
    results = new TreeMap<>();

    for (ExecutableQuery query : queries) {
      results.put(query, new RunResultList());
    }
  }

  public void add(ExecutableQuery query, RunResult runResult) {
    results.get(query).add(runResult);
  }

  public int size() {
    return results.size();
  }

  public List<ExecutableQuery> keys() {
    return new ArrayList<>(results.keySet());
  }

  public RunResultList get(ExecutableQuery key) {
    return results.get(key);
  }

  public Map<ExecutableQuery, RunResultList> getResults() {
    return results;
  }
}
