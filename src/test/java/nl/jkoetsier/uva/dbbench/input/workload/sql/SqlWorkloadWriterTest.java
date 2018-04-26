package nl.jkoetsier.uva.dbbench.input.workload.sql;

import java.util.List;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.output.mssql.workload.MsSqlWorkloadVisitor;
import org.junit.Ignore;
import org.junit.Test;

public class SqlWorkloadWriterTest {

  @Test
  @Ignore
  public void testTest() {
    SqlWorkloadReader reader = new SqlWorkloadReader();

    Workload workload = reader.fromFile("test.sql");


    MsSqlWorkloadVisitor workloadVisitor = new MsSqlWorkloadVisitor();

    workload.acceptVisitor(workloadVisitor);
    List<String> result = workloadVisitor.getResult();

    System.out.println(addNewLines(result.get(0)));
  }

  private String addNewLines(String str) {
    return str.replaceAll("(.{100})", "$1\n");
  }
}
