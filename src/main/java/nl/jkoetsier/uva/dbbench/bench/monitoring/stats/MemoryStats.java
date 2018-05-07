package nl.jkoetsier.uva.dbbench.bench.monitoring.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryStats {

  private static Logger logger = LoggerFactory.getLogger(MemoryStats.class);

  private long usedMemory;
  private long totalMemory;
  private long usedSwap;
  private long totalSwap;
  private long timestamp;

  public long getUsedMemory() {
    return usedMemory;
  }

  public long getTotalMemory() {
    return totalMemory;
  }

  public long getUsedSwap() {
    return usedSwap;
  }

  public long getTotalSwap() {
    return totalSwap;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public static final class MemoryStatsBuilder {

    private long usedMemory;
    private long totalMemory;
    private long usedSwap;
    private long totalSwap;
    private long timestamp;

    private MemoryStatsBuilder() {
    }

    public static MemoryStatsBuilder aMemoryStats() {
      return new MemoryStatsBuilder();
    }

    public MemoryStatsBuilder withUsedMemory(long usedMemory) {
      this.usedMemory = usedMemory;
      return this;
    }

    public MemoryStatsBuilder withTotalMemory(long totalMemory) {
      this.totalMemory = totalMemory;
      return this;
    }

    public MemoryStatsBuilder withUsedSwap(long usedSwap) {
      this.usedSwap = usedSwap;
      return this;
    }

    public MemoryStatsBuilder withTotalSwap(long totalSwap) {
      this.totalSwap = totalSwap;
      return this;
    }

    public MemoryStatsBuilder withTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public MemoryStats build() {
      MemoryStats memoryStats = new MemoryStats();
      memoryStats.timestamp = this.timestamp;
      memoryStats.usedMemory = this.usedMemory;
      memoryStats.totalSwap = this.totalSwap;
      memoryStats.totalMemory = this.totalMemory;
      memoryStats.usedSwap = this.usedSwap;
      return memoryStats;
    }
  }
}
