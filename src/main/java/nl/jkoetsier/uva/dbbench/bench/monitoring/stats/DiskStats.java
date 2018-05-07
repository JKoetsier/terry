package nl.jkoetsier.uva.dbbench.bench.monitoring.stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskStats {

  private static Logger logger = LoggerFactory.getLogger(DiskStats.class);

  private long totalReads;
  private long totalWrites;

  private DiskStats() {
  }

  public long getTotalReads() {
    return totalReads;
  }

  public long getTotalWrites() {
    return totalWrites;
  }

  public static final class DiskStatsBuilder {

    private long totalReads;
    private long totalWrites;

    private DiskStatsBuilder() {
    }

    public static DiskStatsBuilder aDiskStats() {
      return new DiskStatsBuilder();
    }

    public DiskStatsBuilder withTotalReads(long totalReads) {
      this.totalReads = totalReads;
      return this;
    }

    public DiskStatsBuilder withTotalWrites(long totalWrites) {
      this.totalWrites = totalWrites;
      return this;
    }

    public DiskStats build() {
      DiskStats diskStats = new DiskStats();
      diskStats.totalWrites = this.totalWrites;
      diskStats.totalReads = this.totalReads;
      return diskStats;
    }
  }
}
