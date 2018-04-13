package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;
import nl.jkoetsier.uva.dbbench.parser.tsql.TSqlParserFactory;
import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlParser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TSqlSchemaReader implements SchemaReader {

    private TSqlParserFactory tSqlParserFactory;

    public TSqlSchemaReader() {
        tSqlParserFactory = new TSqlParserFactory();
    }

    private TSqlParser getParser(String filename) {
        return tSqlParserFactory.create(filename);
    }

    @Override
    public DataModel fromFile(String fileName) {
        TSqlParser parser = getParser(fileName);
        TSqlSchemaParserListener listener = new TSqlSchemaParserListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parser.tsql_file());

        return listener.getDataModel();
    }
}
