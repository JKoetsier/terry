package nl.jkoetsier.uva.dbbench.bench.monitoring;

import nl.jkoetsier.uva.dbbench.bench.monitoring.stats.SystemStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringThread extends Thread {

  private static Logger logger = LoggerFactory.getLogger(MonitoringThread.class);

  private Monitoring monitoring = new Monitoring();
  private int millis = 50;

  @Override
  public void run() {
    printStats(monitoring.getStats());

    try {
      sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
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
