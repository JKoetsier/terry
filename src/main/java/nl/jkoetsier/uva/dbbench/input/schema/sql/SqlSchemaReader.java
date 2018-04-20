package nl.jkoetsier.uva.dbbench.input.schema.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;
import nl.jkoetsier.uva.dbbench.schema.Schema;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SqlSchemaReader implements SchemaReader {


  static String readFile(String path, Charset encoding) {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));

      return new String(encoded, encoding);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String readFile(String fileName) {
    return readFile(fileName, Charset.defaultCharset());
  }

  @Override
  public Schema fromFile(String fileName) {
    String sql = readFile(fileName);

    try {
      Statements statements = CCJSqlParserUtil.parseStatements(sql);
      return visitTree(statements);

    } catch (JSQLParserException e) {
      throw new RuntimeException(e);
    }
  }

  public Schema visitTree(Statements statements) {
    SqlSchemaStatementVisitor visitor = new SqlSchemaStatementVisitor();
    statements.accept(visitor);

    return visitor.getDataModel();
  }
}
