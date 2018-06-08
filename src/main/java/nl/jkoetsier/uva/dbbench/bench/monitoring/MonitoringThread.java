package nl.jkoetsier.uva.dbbench.bench.monitoring;

import java.util.concurrent.atomic.AtomicBoolean;
import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringThread extends Thread {

  private static Logger logger = LoggerFactory.getLogger(MonitoringThread.class);

  private Monitoring monitoring = new Monitoring();
  private AtomicBoolean running = new AtomicBoolean(true);
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
      printStats(monitoring.getStats());

      try {
        sleep(millis);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.debug("MonitoringThread interrupted");
      }
    }
  }

  public void printStats(SystemStats systemStats) {
    StringBuilder stringBuilder = new StringBuilder("CPU Loads: ");

    for (float f : systemStats.getCpuStats().getCpuCoreLoads()) {
      stringBuilder.append(String.format("%.1f%% ", f * 100));
    }

    System.out.println(stringBuilder.toString());
  }
}
