package nl.jkoetsier.uva.dbbench;

import java.util.Arrays;
import java.util.Set;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.input.schema.sql.SqlSchemaReader;
import nl.jkoetsier.uva.dbbench.input.workload.sql.SqlWorkloadReader;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbbenchApplication implements ApplicationRunner {

  @Value("${workload}")
  private String workloadFile;

  @Value("${datamodel}")
  private String dataModelFile;

  private Boolean verifyWorkload = true;
  private Boolean skipDataModel = false;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(DbbenchApplication.class, args);
  }

  private void checkParameters(ApplicationArguments args) {
    boolean error = false;

    logger.info("Option Names: {}", args.getOptionNames());
    logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
    logger.info("Source args: {}", Arrays.toString(args.getSourceArgs()));

    Set<String> optionNames = args.getOptionNames();

    if (optionNames.contains("no-check-workload")) {
      verifyWorkload = false;
    }

    if (optionNames.contains("skip-datamodel")) {
      verifyWorkload = false;
      skipDataModel = true;
    }

    if (workloadFile.trim().equals("")) {
      System.out.println("No workload provided. Provide workload");
      error = true;
    }

    if (dataModelFile.trim().equals("") && !skipDataModel) {
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
  public void run(ApplicationArguments args) {
    checkParameters(args);

    Schema schema = null;

    if (!skipDataModel) {
      SqlSchemaReader sqlSchemaReader = new SqlSchemaReader();
      schema = sqlSchemaReader.fromFile(dataModelFile);
    }

    SqlWorkloadReader workloadReader = new SqlWorkloadReader();
    Workload workload = workloadReader.fromFile(workloadFile);

    if (verifyWorkload && schema != null) {
      try {
        workload.validate(schema);
      } catch (NotMatchingWorkloadException e) {
        System.out.println(String.format(
            "Error in validating workload: %s", e.getMessage()
        ));

        System.exit(1);
      }
    }

  }
}
