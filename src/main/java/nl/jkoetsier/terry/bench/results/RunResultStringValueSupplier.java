package nl.jkoetsier.terry.bench.results;

@FunctionalInterface
public interface RunResultStringValueSupplier {

  String getValue(RunResult runResult);
}
