package nl.jkoetsier.uva.dbbench.bench.monitoring.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CpuStats {

  private static Logger logger = LoggerFactory.getLogger(CpuStats.class);

  private long userTicks;
  private long niceTicks;
  private long sysTicks;
  private long idleTicks;
  private long ioWaitTicks;
  private long irqTicks;
  private long softIrqTicks;
  private long stealTicks;
  private long totalTicks;
  private long timestamp;

  private float[] cpuCoreLoads;
  private float cpuLoad;
  private float loadAvg1;
  private float loadAvg5;
  private float loadAvg15;

  // Only accessible through builder
  private CpuStats() {
  }

  public float[] getCpuCoreLoads() {
    return cpuCoreLoads;
  }

  public float getCpuLoad() {
    return cpuLoad;
  }

  public float getLoadAvg1() {
    return loadAvg1;
  }

  public float getLoadAvg5() {
    return loadAvg5;
  }

  public float getLoadAvg15() {
    return loadAvg15;
  }

  public long getUserTicks() {
    return userTicks;
  }

  public long getNiceTicks() {
    return niceTicks;
  }

  public long getSysTicks() {
    return sysTicks;
  }

  public long getIdleTicks() {
    return idleTicks;
  }

  public long getIoWaitTicks() {
    return ioWaitTicks;
  }

  public long getIrqTicks() {
    return irqTicks;
  }

  public long getSoftIrqTicks() {
    return softIrqTicks;
  }

  public long getStealTicks() {
    return stealTicks;
  }

  public long getTotalTicks() {

    if (totalTicks == 0) {
      totalTicks = userTicks + niceTicks + sysTicks + idleTicks + ioWaitTicks + irqTicks +
          softIrqTicks + stealTicks;
    }

    return totalTicks;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public static final class CpuStatsBuilder {

    private long userTicks;
    private long niceTicks;
    private long sysTicks;
    private long idleTicks;
    private long ioWaitTicks;
    private long irqTicks;
    private long softIrqTicks;
    private long stealTicks;
    private long timestamp;
    private float[] cpuCoreLoads;
    private float cpuLoad;
    private float loadAvg1;
    private float loadAvg5;
    private float loadAvg15;

    private CpuStatsBuilder() {
    }

    public static CpuStatsBuilder aCpuStats() {
      return new CpuStatsBuilder();
    }

    public CpuStatsBuilder withUserTicks(long userTicks) {
      this.userTicks = userTicks;
      return this;
    }

    public CpuStatsBuilder withNiceTicks(long niceTicks) {
      this.niceTicks = niceTicks;
      return this;
    }

    public CpuStatsBuilder withSysTicks(long sysTicks) {
      this.sysTicks = sysTicks;
      return this;
    }

    public CpuStatsBuilder withIdleTicks(long idleTicks) {
      this.idleTicks = idleTicks;
      return this;
    }

    public CpuStatsBuilder withIoWaitTicks(long ioWaitTicks) {
      this.ioWaitTicks = ioWaitTicks;
      return this;
    }

    public CpuStatsBuilder withIrqTicks(long irqTicks) {
      this.irqTicks = irqTicks;
      return this;
    }

    public CpuStatsBuilder withSoftIrqTicks(long softIrqTicks) {
      this.softIrqTicks = softIrqTicks;
      return this;
    }

    public CpuStatsBuilder withStealTicks(long stealTicks) {
      this.stealTicks = stealTicks;
      return this;
    }

    public CpuStatsBuilder withTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public CpuStatsBuilder withCpuCoreLoads(float[] cpuCoreLoads) {
      this.cpuCoreLoads = cpuCoreLoads;
      return this;
    }

    public CpuStatsBuilder withCpuLoad(float cpuLoad) {
      this.cpuLoad = cpuLoad;
      return this;
    }

    public CpuStatsBuilder withLoadAvg1(float loadAvg1) {
      this.loadAvg1 = loadAvg1;
      return this;
    }

    public CpuStatsBuilder withLoadAvg5(float loadAvg5) {
      this.loadAvg5 = loadAvg5;
      return this;
    }

    public CpuStatsBuilder withLoadAvg15(float loadAvg15) {
      this.loadAvg15 = loadAvg15;
      return this;
    }

    public CpuStats build() {
      CpuStats cpuStats = new CpuStats();
      cpuStats.loadAvg15 = this.loadAvg15;
      cpuStats.ioWaitTicks = this.ioWaitTicks;
      cpuStats.cpuCoreLoads = this.cpuCoreLoads;
      cpuStats.idleTicks = this.idleTicks;
      cpuStats.loadAvg1 = this.loadAvg1;
      cpuStats.userTicks = this.userTicks;
      cpuStats.niceTicks = this.niceTicks;
      cpuStats.sysTicks = this.sysTicks;
      cpuStats.loadAvg5 = this.loadAvg5;
      cpuStats.cpuLoad = this.cpuLoad;
      cpuStats.timestamp = this.timestamp;
      cpuStats.softIrqTicks = this.softIrqTicks;
      cpuStats.irqTicks = this.irqTicks;
      cpuStats.stealTicks = this.stealTicks;
      return cpuStats;
    }
  }
}
