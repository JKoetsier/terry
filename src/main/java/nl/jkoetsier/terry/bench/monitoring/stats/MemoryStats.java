package nl.jkoetsier.terry.bench.monitoring.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryStats implements WritableStats {

  private static Logger logger = LoggerFactory.getLogger(MemoryStats.class);

  private long usedMemory;
  private long totalMemory;
  private long usedSwap;
  private long totalSwap;

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

  public MemoryStats normalise(MemoryStats memoryStats) {
    return memoryStats;
  }

  @Override
  public String[] getHeaders() {
    return new String[]{
        "usedMemory",
        "totalMemory",
        "usedSwap",
        "totalSwap"
    };
  }

  @Override
  public String[] getValues() {
    return new String[]{
        Long.toString(usedMemory),
        Long.toString(totalMemory),
        Long.toString(usedSwap),
        Long.toString(totalSwap)
    };
  }

  public static final class MemoryStatsBuilder {

    private long usedMemory;
    private long totalMemory;
    private long usedSwap;
    private long totalSwap;

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

    public MemoryStats build() {
      MemoryStats memoryStats = new MemoryStats();
      memoryStats.usedMemory = this.usedMemory;
      memoryStats.totalSwap = this.totalSwap;
      memoryStats.totalMemory = this.totalMemory;
      memoryStats.usedSwap = this.usedSwap;
      return memoryStats;
    }
  }
}
