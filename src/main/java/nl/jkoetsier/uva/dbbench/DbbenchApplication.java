package nl.jkoetsier.uva.dbbench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import nl.jkoetsier.uva.dbbench.bench.BenchRunner;
import nl.jkoetsier.uva.dbbench.config.CommandLineConfigProperties;
import nl.jkoetsier.uva.dbbench.config.DbConfigProperties;
import nl.jkoetsier.uva.dbbench.config.GlobalConfigProperties;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.docker.DockerContainer;
import nl.jkoetsier.uva.dbbench.input.SchemaReader;
import nl.jkoetsier.uva.dbbench.input.WorkloadReader;
import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.jkoetsier.uva.dbbench")
public class DbbenchApplication implements ApplicationRunner {

  @Autowired
  private CommandLineConfigProperties commandLineConfigProperties;

  @Autowired
  private GlobalConfigProperties globalConfigProperties;

  @Autowired
  private DbConfigProperties dbConfigProperties;

  @Autowired
  private SchemaReader schemaReader;

  @Autowired
  private WorkloadReader workloadReader;

  @Autowired
  private DatabaseConnector databaseConnector;

  private Boolean verifyWorkload = true;
  private Boolean skipDataModel = false;

  private static Logger logger = LoggerFactory.getLogger(DbbenchApplication.class);

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(DbbenchApplication.class, args);

    for (String name : applicationContext.getBeanDefinitionNames()) {
      logger.debug("Have bean: {}", name);
    }
  }

  private void checkParameters(ApplicationArguments args) {
    boolean error = false;

    logger.debug("Option Names: {}", args.getOptionNames());
    logger.debug("NonOptionArgs: {}", args.getNonOptionArgs());
    logger.debug("Source args: {}", Arrays.toString(args.getSourceArgs()));

    Set<String> optionNames = args.getOptionNames();

    List<String> options = new ArrayList<>();
    options.add("--no-check-workload");
    options.add("--skip-datamodel");
    options.add("--stop-container=(true|false)");

    if (optionNames.contains("no-check-workload")) {
      verifyWorkload = false;
    }

    if (optionNames.contains("skip-datamodel")) {
      verifyWorkload = false;
      skipDataModel = true;
    }

    if (commandLineConfigProperties.getWorkload().trim().equals("")) {
      System.err.println("No workload provided. Provide workload");
      error = true;
    }

    if (commandLineConfigProperties.getDatamodel().trim().equals("") && !skipDataModel) {
      System.err.println("No datamodel provided. Provide datamodel");
      error = true;
    }

    if (commandLineConfigProperties.getOutputDb().trim().equals("") ||
        !globalConfigProperties.getAcceptedDatabases()
            .contains(commandLineConfigProperties.getOutputDb())) {
      System.err.println("No correct output database provided. Provide output database");
    }

    if (error) {
      System.err.format(
          "\nRun program with parameters:\n --workload=workloadfile.sql\n --datamodel=datamodel.sql\n "
              + "--output_db=(%s)\nOptional:\n %s%n",
          String.join("|", globalConfigProperties.getAcceptedDatabases()),
          String.join("\n ", options)
      );
      System.exit(1);
    }
  }

  @Override
  public void run(ApplicationArguments args) {
    checkParameters(args);

    Schema schema = null;

    if (!skipDataModel) {
      schema = schemaReader.fromFile(commandLineConfigProperties.getDatamodel());
    }

    Workload workload = workloadReader.fromFile(commandLineConfigProperties.getWorkload());

    if (verifyWorkload && schema != null) {
      try {
        workload.validate(schema);
      } catch (NotMatchingWorkloadException e) {
        System.err.format(
            "Error in validating workload: %s%n", e.getMessage()
        );

        System.exit(1);
      }
    }

    logger.info("Output database: {}", commandLineConfigProperties.getOutputDb());

    BenchRunner benchRunner = new BenchRunner(databaseConnector, globalConfigProperties);

    logger.info("Is docker: {}", databaseConnector.isDocker());

    DockerContainer dockerContainer = null;

    try {

      if (databaseConnector.isDocker()) {

        if (!validateDockerConfig()) {
          System.err.println("Missing docker settings");
          System.exit(1);
        }

        Integer port = globalConfigProperties.getDefaultPort();
        dockerContainer = new DockerContainer(dbConfigProperties.getDockerImage());
        dockerContainer.addPortMapping(port, dbConfigProperties.getDefaultPort());
        dockerContainer.setReadyLogLine(dbConfigProperties.getDockerReadyLogLine());

        if (dbConfigProperties.getDockerEnvvars() != null) {
          dockerContainer.addEnvironmentVariables(dbConfigProperties.getDockerEnvvars());
        }

        dockerContainer.run();

        dbConfigProperties.setPort(port);
      }

      benchRunner.setWorkload(workload);

      if (schema != null) {
        benchRunner.setSchema(schema);
      }

      benchRunner.run();

    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      if (dockerContainer != null && commandLineConfigProperties.isStopContainer()) {
        dockerContainer.stop();
      } else {
        logger.info("Keeping container running");
      }
    }

  }

  private boolean validateDockerConfig() {
    return !(dbConfigProperties.getDockerImage() == null ||
        dbConfigProperties.getDefaultPort() == null ||
        dbConfigProperties.getDockerReadyLogLine() == null
    );
  }
}
