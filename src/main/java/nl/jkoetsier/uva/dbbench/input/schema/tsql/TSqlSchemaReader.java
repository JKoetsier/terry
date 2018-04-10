package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlLexer;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlParser;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlParserListener;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlParserVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class TSqlSchemaReader implements SchemaReader {


    private TSqlParser getParser(String filename) {

        try {
            TSqlLexer lexer = new TSqlLexer(CharStreams.fromFileName(filename));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            return new TSqlParser(tokenStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file " + filename);
        }
    }

    @Override
    public DataModel fromFile(String fileName) {
        TSqlParser parser = getParser(fileName);
        TSqlParserListenerImpl listener = new TSqlParserListenerImpl();

        listener.enterTsql_file(parser.tsql_file());
        return listener.getDataModel();

    }
}
