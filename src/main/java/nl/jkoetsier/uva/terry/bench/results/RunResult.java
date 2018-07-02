package nl.jkoetsier.uva.terry.bench.results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunResult {

  private static Logger logger = LoggerFactory.getLogger(RunResult.class);

  private long responseTimeNs;
  private long resultsTimeNs;
  private int resultLength;
  private int resultWidth;
  private boolean valid = true;
  private boolean supported = true;

  public RunResult() {
  }

  public RunResult(long responseTimeNs, long resultsTimeNs) {
    this.responseTimeNs = responseTimeNs;
    this.resultsTimeNs = resultsTimeNs;
  }

  public long getResponseTimeNs() {
    return responseTimeNs;
  }

  public void setResponseTimeNs(long responseTimeNs) {
    this.responseTimeNs = responseTimeNs;
  }

  public long getResultsTimeNs() {
    return resultsTimeNs;
  }

  public void setResultsTimeNs(long resultsTimeNs) {
    this.resultsTimeNs = resultsTimeNs;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public boolean isSupported() {
    return supported;
  }

  public void setSupported(boolean supported) {
    this.supported = supported;
  }

  public int getResultLength() {
    return resultLength;
  }

  public void setResultLength(int resultLength) {
    this.resultLength = resultLength;
  }

  public int getResultWidth() {
    return resultWidth;
  }

  public void setResultWidth(int resultWidth) {
    this.resultWidth = resultWidth;
  }
}
