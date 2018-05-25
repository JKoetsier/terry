package nl.jkoetsier.uva.dbbench.development;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import nl.jkoetsier.uva.dbbench.connector.mssql.workload.MsSqlWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSqlTransformTest {

  private static Logger logger = LoggerFactory.getLogger(MsSqlTransformTest.class);

  private String inputFile = "dev/biqh_workload.sql";
  private int start = 32;
  private int count = 100;

  private String readFile(String filename) {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(filename));

      return new String(encoded, Charset.defaultCharset());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String[] getQueries(String filename) {
    String file = readFile(filename);

    return file.split(";");
  }

  private String getOutputQuery(String inputQuery) {
    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    Workload workload = workloadReader.fromString(inputQuery);

    Collection<Query> queries = workload.getQueries().values();

    assertEquals(1, queries.size());

    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    Query query = queries.iterator().next();

    query.acceptVisitor(workloadVisitor);

    Collection<String> queriesAsString = workloadVisitor.getResult().values();

    assertEquals(1, queriesAsString.size());

    return queriesAsString.iterator().next();
  }

  private String formatQuery(String query) {

    // Prepend newlines
    String result = query.replaceAll("(?i)(SELECT|FROM|ORDER|WHERE|OFFSET|AND|LEFT|INNER|ON )", "\n$1");

    // Append newlines
    result = result.replaceAll("(?i)(SELECT|,)", "$1\n");

    // Remove duplicate newlines
    result = result.replaceAll("(\n)+", "\n");
    return result;
  }

  private String stripQuery(String query) {
    String result = query;
    result = result.replaceAll("dbo", "");
    result = result.replaceAll("ASC", "");
    result = result.replaceAll("(?i)outer join", "join");
    result = result.replaceAll("(?i)inner join", "join");
    result = result.replaceAll("(?i) AS ", "");
    result = result.replaceAll("\\W", "");
    result = result.toLowerCase();

    return result;
  }

  @Test
  @Ignore
  public void testWorkload() {
    String[] queries = getQueries(inputFile);

    for (int i = start; i < queries.length && i < start + count; i++) {
      String inputQuery = queries[i];
      System.out.println("INPUTQUERY RAW: " + inputQuery);
      String outputQuery = getOutputQuery(inputQuery);
      System.out.println("INPUT " + i + ": " + formatQuery(inputQuery));
      System.out.println();
      System.out.println("OUTPUT " + i + ": " + formatQuery(outputQuery));
      System.out.println("==========================================================");

      String output2Query = getOutputQuery(formatQuery(outputQuery));


      assertEquals(stripQuery(inputQuery), stripQuery(outputQuery));
      // Check reflexivity
      assertEquals(output2Query, outputQuery);
    }
  }
}
