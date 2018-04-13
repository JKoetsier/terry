package nl.jkoetsier.uva.dbbench.parser.tsql;

import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlLexer;
import nl.jkoetsier.uva.dbbench.parser.tsql.grammar.TSqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class TSqlParserFactory {

    public TSqlParser create(String filename) {

        try {
            TSqlLexer lexer = new TSqlLexer(CharStreams.fromFileName(filename));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            return new TSqlParser(tokenStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file " + filename);
        }
    }
}
