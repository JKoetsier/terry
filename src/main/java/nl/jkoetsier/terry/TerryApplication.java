package nl.jkoetsier.terry;

import nl.jkoetsier.terry.config.DbConfigProperties;
import nl.jkoetsier.terry.docker.DockerContainer;
import nl.jkoetsier.terry.input.SchemaReader;
import nl.jkoetsier.terry.input.WorkloadReader;
import nl.jkoetsier.terry.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.terry.intrep.schema.Schema;
import nl.jkoetsier.terry.intrep.workload.Workload;
import nl.jkoetsier.terry.bench.BenchPreparer;
import nl.jkoetsier.terry.bench.BenchRunner;
import nl.jkoetsier.terry.bench.DatabaseInitialiser;
import nl.jkoetsier.terry.bench.indexcreator.IndexCreator;
import nl.jkoetsier.terry.config.ApplicationConfigProperties;
import nl.jkoetsier.terry.config.DsvConfigProperties;
import nl.jkoetsier.terry.connector.DatabaseConnector;
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
@ComponentScan("nl.jkoetsier.terry")
public class TerryApplication implements ApplicationRunner {

  private static Logger logger = LoggerFactory.getLogger(TerryApplication.class);


  @Autowired
  private ApplicationConfigProperties applicationConfigProperties;

  @Autowired
  private DbConfigProperties dbConfigProperties;

  @Autowired
  private SchemaReader schemaReader;

  @Autowired
  private WorkloadReader workloadReader;

  @Autowired
  private DatabaseConnector databaseConnector;

  @Autowired
  private DsvConfigProperties dsvConfigProperties;


  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(TerryApplication.class, args);

    for (String name : applicationContext.getBeanDefinitionNames()) {
      logger.debug("Have bean: {}", name);
    }
  }

  @Override
  public void run(ApplicationArguments args) {
    checkParameters();

    Schema schema = schemaReader.fromFile(applicationConfigProperties.getSchema());
    Workload workload = workloadReader.fromFile(applicationConfigProperties.getWorkload());

    if (applicationConfigProperties.isCheckWorkload()) {
      try {
        workload.validate(schema);
      } catch (NotMatchingWorkloadException e) {
        throw new RuntimeException(e);
      }
    }

    logger.info("Output database: {}", applicationConfigProperties.getDbType());

    BenchRunner benchRunner = new BenchRunner(databaseConnector, applicationConfigProperties,
        schema, workload);

    logger.info("Is docker: {}", databaseConnector.isDocker());

    DockerContainer dockerContainer = null;

    try {

      if (databaseConnector.isDocker()) {
        dockerContainer = setupDocker();
      }

      DatabaseInitialiser databaseInitialiser = new DatabaseInitialiser(databaseConnector, schema);
      databaseInitialiser.setSkipCreateSchema(applicationConfigProperties.isSkipCreateSchema());

      if (!applicationConfigProperties.getDataDirectory().equals("")) {
        databaseInitialiser.setDataDirectory(applicationConfigProperties.getDataDirectory());
      }

      databaseInitialiser.initialise();

      BenchPreparer benchPreparer = new BenchPreparer(databaseConnector, schema, workload);
      benchPreparer.prepare();

      if (applicationConfigProperties.createIndices()) {
        IndexCreator indexCreator = new IndexCreator(databaseConnector, schema, workload);
        indexCreator.createIndices();
      }

      benchRunner.run();

    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      if (dockerContainer != null && applicationConfigProperties.isStopContainer()) {
        dockerContainer.stop();
      } else if (dockerContainer != null) {
        logger.info("Keeping container running");
      }
    }

  }

  private DockerContainer setupDocker() {
    if (!validateDockerConfig()) {
      System.err.println("Missing docker settings");
      System.exit(1);
    }

    Integer port = applicationConfigProperties.getDefaultPort();
    DockerContainer dockerContainer = new DockerContainer(dbConfigProperties.getDockerImage());
    dockerContainer.addPortMapping(port, dbConfigProperties.getDefaultDbPort());
    dockerContainer.setReadyLogLine(dbConfigProperties.getDockerReadyLogLine());

    if (dbConfigProperties.getDockerEnvvars() != null) {
      dockerContainer.addEnvironmentVariables(dbConfigProperties.getDockerEnvvars());
    }

    if (!applicationConfigProperties.getDataDirectory().equals("")) {
      // Map data directory to docker Volume
      dockerContainer.addVolumeMapping(
          applicationConfigProperties.getDataDirectory(),
          dbConfigProperties.getDockerDataDirectory()
      );
    }

    dockerContainer.run();

    dbConfigProperties.setPort(port);

    return dockerContainer;
  }

  private boolean validateDockerConfig() {
    return !(dbConfigProperties.getDockerImage() == null ||
        dbConfigProperties.getDefaultDbPort() == null ||
        dbConfigProperties.getDockerReadyLogLine() == null
    );
  }

  private void checkParameters() {
    boolean error = false;

    if (applicationConfigProperties.getWorkload().trim().equals("")) {
      System.err.println("No workload provided. Provide workload");
      error = true;
    }

    if (applicationConfigProperties.getSchema().trim().equals("")) {
      System.err.println("No schema provided. Provide schema");
      error = true;
    }

    if (applicationConfigProperties.getDbType().trim().equals("") ||
        !applicationConfigProperties.getAcceptedDatabases()
            .contains(applicationConfigProperties.getDbType())) {
      System.err.println("No correct output database provided. Provide output database");
    }

    if (error) {
      System.err.format(
          "\nRun program with parameters:\n --workload=workloadfile.sql\n --schema=schema.sql\n "
              + "--outputdb=(%s)%n",
          String.join("|", applicationConfigProperties.getAcceptedDatabases())
      );
      System.exit(1);
    }
  }
}
