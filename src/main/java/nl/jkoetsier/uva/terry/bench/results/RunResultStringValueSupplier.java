package nl.jkoetsier.uva.terry.bench.results;

@FunctionalInterface
public interface RunResultStringValueSupplier {

  String getValue(RunResult runResult);
}
