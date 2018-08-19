package nl.jkoetsier.terry.bench.results;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunResultList {

  private static Logger logger = LoggerFactory.getLogger(RunResultList.class);

  private List<RunResult> list;

  public RunResultList() {
    this.list = new ArrayList<>();
  }

  public void add(RunResult runResult) {
    list.add(runResult);
  }

  public int size() {
    return list.size();
  }

  public RunResult get(int i) {
    return list.get(i);
  }

  public Double getAverageResponseTime() {
    Long total = 0L;

    for (RunResult runResult : list) {
      total += runResult.getResponseTimeNs();
    }

    return Double.valueOf(total) / list.size();
  }

  public Double getAverageResultsTime() {
    Long total = 0L;

    for (RunResult runResult : list) {
      total += runResult.getResultsTimeNs();
    }

    return Double.valueOf(total) / list.size();
  }
}
