package nl.jkoetsier.terry.bench.monitoring;

import java.util.concurrent.atomic.AtomicBoolean;
import nl.jkoetsier.terry.bench.monitoring.stats.SystemStats;
import nl.jkoetsier.terry.bench.monitoring.stats.SystemStatsCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringThread extends Thread {

  private static Logger logger = LoggerFactory.getLogger(MonitoringThread.class);

  private Monitoring monitoring = new Monitoring();
  private AtomicBoolean running = new AtomicBoolean(true);
  private SystemStatsCollection systemStatsCollection = new SystemStatsCollection();
  private int millis = 500;

  public void interrupt() {
    running.set(false);
    super.interrupt();
  }

  public void end() {
    running.set(false);
  }

  @Override
  public void run() {
    while (running.get()) {
      collectStats();

      try {
        sleep(millis);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.debug("MonitoringThread interrupted");
      }
    }
  }

  private void collectStats() {
    SystemStats systemStats = monitoring.getStats();
    systemStatsCollection.add(systemStats);
  }

  public SystemStatsCollection getSystemStatsCollection() {
    return systemStatsCollection;
  }
}
