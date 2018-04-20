package nl.jkoetsier.uva.dbbench.bench;

import nl.jkoetsier.uva.dbbench.output.DatabaseInterface;

public class BenchRunner {

  private DatabaseInterface databaseInterface;

  public BenchRunner(DatabaseInterface databaseInterface) {
    this.databaseInterface = databaseInterface;
  }

  private void setup() {
    databaseInterface.start();
    databaseInterface.connect();
  }

  private void tearDown() {
    databaseInterface.closeConnection();
  }

  public void run() {

  }
}
