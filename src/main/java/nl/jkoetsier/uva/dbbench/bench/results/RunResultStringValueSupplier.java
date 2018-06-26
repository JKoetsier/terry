package nl.jkoetsier.uva.dbbench.bench.results;

import nl.jkoetsier.uva.dbbench.bench.results.RunResult;

@FunctionalInterface
public interface RunResultStringValueSupplier {

  String getValue(RunResult runResult);
}
