package nl.jkoetsier.terry.bench.monitoring;

import nl.jkoetsier.terry.bench.monitoring.stats.CpuStats;
import nl.jkoetsier.terry.bench.monitoring.stats.CpuStats.CpuStatsBuilder;
import nl.jkoetsier.terry.bench.monitoring.stats.DiskStats;
import nl.jkoetsier.terry.bench.monitoring.stats.DiskStats.DiskStatsBuilder;
import nl.jkoetsier.terry.bench.monitoring.stats.MemoryStats;
import nl.jkoetsier.terry.bench.monitoring.stats.MemoryStats.MemoryStatsBuilder;
import nl.jkoetsier.terry.bench.monitoring.stats.SystemStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

public class Monitoring {

  private static Logger logger = LoggerFactory.getLogger(Monitoring.class);

  private SystemInfo systemInfo;
  private HardwareAbstractionLayer hal;

  public Monitoring() {
    systemInfo = new SystemInfo();
    hal = systemInfo.getHardware();
  }

  public SystemStats getStats() {

    MemoryStats memoryStats = getMemoryStats();
    CpuStats cpuStats = getCpuStats();
    DiskStats diskStats = getDiskStats();
    SystemStats systemStats = new SystemStats(cpuStats, memoryStats, diskStats);

    return systemStats;
  }

  public CpuStats getCpuStats() {
    CentralProcessor processor = hal.getProcessor();

    long[] ticks = processor.getSystemCpuLoadTicks();
    double[] dCpuLoads = processor.getProcessorCpuLoadBetweenTicks();
    float[] cpuLoads = new float[dCpuLoads.length];

    for (int i = 0; i < dCpuLoads.length; i++) {
      cpuLoads[i] = (float) dCpuLoads[i];
    }

    double[] loadAverages = processor.getSystemLoadAverage(3);

    return CpuStatsBuilder
        .aCpuStats()
        .withUserTicks(ticks[TickType.USER.getIndex()])
        .withNiceTicks(ticks[TickType.NICE.getIndex()])
        .withSysTicks(ticks[TickType.SYSTEM.getIndex()])
        .withIdleTicks(ticks[TickType.IDLE.getIndex()])
        .withIoWaitTicks(ticks[TickType.IOWAIT.getIndex()])
        .withIrqTicks(ticks[TickType.IRQ.getIndex()])
        .withSoftIrqTicks(ticks[TickType.SOFTIRQ.getIndex()])
        .withStealTicks(ticks[TickType.STEAL.getIndex()])
        .withCpuLoad((float) processor.getSystemCpuLoad() * 100)
        .withCpuCoreLoads(cpuLoads)
        .withLoadAvg1((float) loadAverages[0])
        .withLoadAvg5((float) loadAverages[1])
        .withLoadAvg15((float) loadAverages[2])
        .build();
  }

  public MemoryStats getMemoryStats() {
    GlobalMemory memory = hal.getMemory();

    return MemoryStatsBuilder
        .aMemoryStats()
        .withTotalMemory(memory.getTotal())
        .withUsedMemory(memory.getTotal() - memory.getAvailable())
        .withUsedSwap(memory.getSwapUsed())
        .withTotalSwap(memory.getSwapTotal())
        .build();
  }


  public DiskStats getDiskStats() {
    HWDiskStore[] diskStores = hal.getDiskStores();

    long totalWrites = 0;
    long totalReads = 0;

    for (HWDiskStore diskStore : diskStores) {
      totalReads += diskStore.getReads();
      totalWrites += diskStore.getWrites();
    }

    return DiskStatsBuilder
        .aDiskStats()
        .withTotalReads(totalReads)
        .withTotalWrites(totalWrites)
        .build();
  }
}
