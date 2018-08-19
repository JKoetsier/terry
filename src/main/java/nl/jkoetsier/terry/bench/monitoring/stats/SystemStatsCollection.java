package nl.jkoetsier.terry.bench.monitoring.stats;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemStatsCollection {

  private static Logger logger = LoggerFactory.getLogger(SystemStatsCollection.class);

  private List<SystemStats> systemStatsList;

  public SystemStatsCollection() {
    systemStatsList = new ArrayList<>();
  }

  public void add(SystemStats systemStats) {
    systemStatsList.add(systemStats);
  }

  public List<SystemStats> getAll() {
    return systemStatsList;
  }

  public SystemStatsCollection normalise() {
    SystemStatsCollection normalisedCollection = new SystemStatsCollection();

    if (systemStatsList.size() == 0) {
      return normalisedCollection;
    }

    for (SystemStats systemStats : systemStatsList) {
      SystemStats normalisedStats = systemStats.normalise(systemStatsList.get(0));
      normalisedCollection.add(normalisedStats);
    }

    return normalisedCollection;
  }
}
