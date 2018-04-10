package nl.jkoetsier.uva.dbbench.input.schema.tsql;

import nl.jkoetsier.uva.dbbench.datamodel.DataModel;
import nl.jkoetsier.uva.dbbench.datamodel.Entity;
import nl.jkoetsier.uva.dbbench.datamodel.fields.*;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlLexer;
import nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar.TSqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class TSqlParserListenerImplTest {
    String testDataDir = getTestDataDir();

    private String getTestDataDir() {
        String base = "src/test/java/";

        return base + getPackageName().replace(".", "/") + "/grammar/data/";
    }

    private String getPackageName() {
        return this.getClass().getPackage().getName();
    }

    private TSqlParser getParser(String inputFile) {
        try {
            TSqlLexer lexer = new TSqlLexer(CharStreams.fromFileName(testDataDir + inputFile));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            return new TSqlParser(tokens);
        } catch (IOException e) {
            throw new RuntimeException("Could not find input file");
        }
    }

    @Test
    public void testCreateTable() {
        TSqlParser parser = getParser("create_table.sql");

        TSqlParserListenerImpl listener = new TSqlParserListenerImpl();
        listener.enterCreate_table(
                parser.tsql_file().batch(1).sql_clauses().sql_clause(0).ddl_clause().create_table()
        );

        DataModel dataModel = listener.getDataModel();
        assertEquals(dataModel.getEntities().size(), 1);

        Entity entity = dataModel.getEntities().get(0);
        assertEquals(entity.getFields().size(), 9);

        assertEquals("TableName", entity.getName());

        List<Field> fields = entity.getFields();

        assertEquals("Id", fields.get(0).getName());
        assertEquals("IntField", fields.get(1).getName());

        assertTrue(fields.get(0) instanceof IntegerField);
        assertTrue(fields.get(2) instanceof VarCharField);
        assertTrue(fields.get(6) instanceof DateTimeField);
        assertTrue(fields.get(7) instanceof BooleanField);
    }
}
