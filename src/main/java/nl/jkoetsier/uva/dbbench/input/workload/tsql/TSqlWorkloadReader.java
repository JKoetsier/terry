package nl.jkoetsier.uva.dbbench.input.workload.tsql;

import nl.jkoetsier.uva.dbbench.input.WorkloadReader;
import nl.jkoetsier.uva.dbbench.workload.Workload;
import nl.jkoetsier.uva.dbbench.parser.tsql.TSqlParserFactory;
import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlParser;

public class TSqlWorkloadReader implements WorkloadReader {

    private TSqlParserFactory tSqlParserFactory;

    public TSqlWorkloadReader() {
        tSqlParserFactory = new TSqlParserFactory();
    }

    private TSqlParser getParser(String filename) {
        return tSqlParserFactory.create(filename);
    }

    @Override
    public Workload fromFile(String fileName) {
        TSqlParser parser = getParser(fileName);
        TSqlWorkloadParserVisitor visitor = new TSqlWorkloadParserVisitor();

        visitor.visitTsql_file(parser.tsql_file());

        return new Workload(visitor.getQueries());
    }
}
