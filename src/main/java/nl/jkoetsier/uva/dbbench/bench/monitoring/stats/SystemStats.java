package nl.jkoetsier.uva.dbbench.bench.monitoring.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemStats {

  private static Logger logger = LoggerFactory.getLogger(SystemStats.class);

  private CpuStats cpuStats;
  private MemoryStats memoryStats;
  private DiskStats diskStats;

  public SystemStats(CpuStats cpuStats,
      MemoryStats memoryStats, DiskStats diskStats) {
    this.cpuStats = cpuStats;
    this.memoryStats = memoryStats;
    this.diskStats = diskStats;
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
}
