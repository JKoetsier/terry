package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.mssql.workload.MsSqlWorkloadVisitor;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlWorkloadWriterTest {

  @Test
  @Ignore
  public void testTest() {
    SqlWorkloadReader reader = new SqlWorkloadReader();

    Workload workload = reader.fromFile("dev/test.sql");

    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();
    workloadVisitor.setFormat(true);

    workload.acceptVisitor(workloadVisitor);
    HashMap<Integer, String> result = workloadVisitor.getResult();

//    System.out.println(addNewLines(result.get(0)));

    //String expected = SqlWorkloadReader.readFile("test.sql", Charset.defaultCharset());
    //assertEquals(cleanupString(expected), cleanupString(result.get(0)));
  }

  private String cleanupString(String str) {
    return str.replaceAll("(\\s|dbo\\.)", "");
  }


  private String addNewLines(String str) {
    return str.replaceAll("(.{100})", "$1\n");
  }
}
