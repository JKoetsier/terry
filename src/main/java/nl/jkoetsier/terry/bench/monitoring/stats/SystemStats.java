package nl.jkoetsier.terry.bench.monitoring.stats;

import nl.jkoetsier.terry.bench.monitoring.util.StringArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemStats implements WritableStats {

  private static Logger logger = LoggerFactory.getLogger(SystemStats.class);

  private CpuStats cpuStats;
  private MemoryStats memoryStats;
  private DiskStats diskStats;
  private long timeStampMillis;

  public SystemStats(CpuStats cpuStats,
      MemoryStats memoryStats, DiskStats diskStats) {
    this.cpuStats = cpuStats;
    this.memoryStats = memoryStats;
    this.diskStats = diskStats;
    this.timeStampMillis = System.currentTimeMillis();
  }

  public CpuStats getCpuStats() {
    return cpuStats;
  }

  public MemoryStats getMemoryStats() {
    return memoryStats;
  }

  public DiskStats getDiskStats() {
    return diskStats;
  }

  public long getTimeStampMillis() {
    return timeStampMillis;
  }

  public void setTimeStampMillis(long timeStampMillis) {
    this.timeStampMillis = timeStampMillis;
  }

  public SystemStats normalise(SystemStats baseStats) {
    SystemStats normalised = new SystemStats(
        cpuStats.normalise(baseStats.getCpuStats()),
        memoryStats.normalise(baseStats.getMemoryStats()),
        diskStats.normalise(baseStats.getDiskStats())
    );

    normalised.setTimeStampMillis(timeStampMillis - baseStats.getTimeStampMillis());

    return normalised;
  }


  @Override
  public String[] getHeaders() {
    return StringArray.concatArrays(
        new String[]{"time"},
        cpuStats.getHeaders(),
        memoryStats.getHeaders(),
        diskStats.getHeaders()
    );
  }

  @Override
  public String[] getValues() {
    return StringArray.concatArrays(
        new String[]{Long.toString(timeStampMillis)},
        cpuStats.getValues(),
        memoryStats.getValues(),
        diskStats.getValues()
    );
  }
}
