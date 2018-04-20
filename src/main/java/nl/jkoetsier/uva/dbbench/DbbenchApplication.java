package nl.jkoetsier.uva.dbbench;

import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.workload.Workload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbbenchApplication implements CommandLineRunner {

  @Value("${workload}")
  private String workloadFile;

  @Value("${datamodel}")
  private String dataModelFile;

  public static void main(String[] args) {
    SpringApplication.run(DbbenchApplication.class, args);
  }

  private void checkParameters() {
    boolean error = false;

    if (workloadFile.trim().equals("")) {
      System.out.println("No workload provided. Provide workload");
      error = true;
    }

    if (dataModelFile.trim().equals("")) {
      System.out.println("No datamodel provided. Provide datamodel");
      error = true;
    }

    if (error) {
      System.out.println(
          "Run program with parameters --workload=workloadfile.sql and --datamodel=datamodel.sql");
      System.exit(1);
    }
  }

  @Override
  public void run(String... args) {
    checkParameters();

    SqlSchemaReader sqlSchemaReader = new SqlSchemaReader();
    sqlSchemaReader.fromFile(dataModelFile);

//    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
//    Workload workload = workloadReader.fromFile(workloadFile);
  }
}
