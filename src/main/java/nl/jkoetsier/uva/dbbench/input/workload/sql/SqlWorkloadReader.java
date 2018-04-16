package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;
import nl.jkoetsier.uva.dbbench.input.WorkloadReader;
import nl.jkoetsier.uva.dbbench.workload.Workload;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SqlWorkloadReader implements WorkloadReader {

    private String readFile(String fileName) {
        return readFile(fileName, Charset.defaultCharset());
    }

    static String readFile(String path, Charset encoding) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));

            return new String(encoded, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Workload fromFile(String fileName) {
        String sql = readFile(fileName);

        return fromString(sql);
    }

    @Override
    public Workload fromString(String sql) {
        try {
            Statements statements = CCJSqlParserUtil.parseStatements(sql);
            return visitTree(statements);

        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }

    public Workload visitTree(Statements statements) {
        SqlWorkloadStatementVisitor visitor = new SqlWorkloadStatementVisitor();
        statements.accept(visitor);

        return visitor.getWorkload();
    }
}
