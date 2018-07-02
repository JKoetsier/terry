package nl.jkoetsier.uva.terry.input.workload.sql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;
import nl.jkoetsier.uva.terry.input.WorkloadReader;
import nl.jkoetsier.uva.terry.input.yaml.YamlQuery;
import nl.jkoetsier.uva.terry.input.yaml.YamlWorkload;
import nl.jkoetsier.uva.terry.internal.QueryResult;
import nl.jkoetsier.uva.terry.internal.QueryResultRow;
import nl.jkoetsier.uva.terry.internal.workload.Query;
import nl.jkoetsier.uva.terry.internal.workload.Workload;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlWorkloadReader implements WorkloadReader {

  private Logger logger = LoggerFactory.getLogger(SqlWorkloadReader.class);

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
  public Workload fromFile(String fileName) {
    if (fileName.endsWith(".yml")) {
      logger.debug("Reading YAML file");
      return fromYamlFile(fileName);
    }

    String sql = readFile(fileName);

    return fromString(sql);
  }

  private Workload fromYamlFile(String fileName) {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    try {
      YamlWorkload yamlWorkload = objectMapper.readValue(new File(fileName), YamlWorkload.class);

      return createWorkload(yamlWorkload);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private Workload createWorkload(YamlWorkload yamlWorkload) {
    Workload workload = new Workload();

    for (YamlQuery yamlQuery : yamlWorkload.getWorkload()) {
      Statements statements = getStatements(yamlQuery.getQuery());
      StatementVisitor visitor = new StatementVisitor();
      statements.accept(visitor);

      List<Query> queryList = visitor.getQueries();

      Query query = queryList.get(0);
      query.setIdentifier(yamlQuery.getIdentifier());

      QueryResult queryResult = new QueryResult();
      List<QueryResultRow> resultRows = new ArrayList<>();

      if (yamlQuery.getResults() != null) {
        for (String resultRow : yamlQuery.getResults()) {
          QueryResultRow queryResultRow = new QueryResultRow();

          try {

            // Use CSV Parser to parse each line as a csv record (thereby supporting comma's in
            // records)
            CSVParser csvParser = CSVParser.parse(resultRow, CSVFormat.DEFAULT);
            CSVRecord record = csvParser.getRecords().get(0);

            String[] values = new String[record.size()];

            for (int i = 0; i < values.length; i++) {
              values[i] = record.get(i);
            }

            queryResultRow.setValues(values);

          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          resultRows.add(queryResultRow);
        }
      }

      queryResult.setRows(resultRows);

      query.setExpectedResult(queryResult);

      workload.addQuery(query);
    }

    return workload;
  }

  private Statements getStatements(String sql) {
    try {
      Statements statements = CCJSqlParserUtil.parseStatements(sql);
      return statements;

    } catch (JSQLParserException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Workload fromString(String sql) {
    Statements statements = getStatements(sql);

    return visitTreeForWorkload(statements);
  }

  public Workload visitTreeForWorkload(Statements statements) {
    StatementVisitor visitor = new StatementVisitor();
    statements.accept(visitor);

    return new Workload(visitor.getQueries());
  }
}
