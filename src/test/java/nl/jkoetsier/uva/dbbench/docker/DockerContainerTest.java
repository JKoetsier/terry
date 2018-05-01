package nl.jkoetsier.uva.dbbench.docker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerConfig;
import com.github.dockerjava.api.model.ContainerPort;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.dbbench.docker.exception.NotExistingContainerException;
import nl.jkoetsier.uva.dbbench.testclass.IntegrationTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DockerContainerTest {

  private String testImage = "hello-world";
  private static String containerName = "testcontainer92824912240djkld";

  private DockerContainer dockerContainer;

  @BeforeClass
  public static void removeContainer() {
    // Clean up possible mess from a previous run
    try {
      DockerContainer existingContainer = DockerContainer.fromExisting(containerName);

      if (existingContainer.isRunning()) {
        existingContainer.stop();
      }
      existingContainer.remove();
    } catch (NotExistingContainerException e) {
      // Do nothing
    }
  }

  @After
  public void cleanup() {
    if (dockerContainer != null) {
      dockerContainer.stop();
      dockerContainer.remove();
      dockerContainer = null;
    }
  }

  @Test
  @Category(IntegrationTest.class)
  public void testDockerRun() {
    dockerContainer = new DockerContainer(testImage);
    dockerContainer.setName(containerName);
    dockerContainer.run();

    assertEquals("running", dockerContainer.getContainer().getState());

    List<String> names = Arrays.asList(dockerContainer.getContainer().getNames());
    assertTrue(names.contains("/" + containerName));
  }

  @Test
  @Category(IntegrationTest.class)
  public void testDockerRunWithPorts() {
    dockerContainer = new DockerContainer(testImage);

    HashMap<Integer, Integer> portMapping = new HashMap<>();
    portMapping.put(3500, 3700);
    portMapping.put(3600, 3800);
    dockerContainer.setPortMapping(portMapping);

    dockerContainer.setName(containerName);
    dockerContainer.run();

    Container container = dockerContainer.getContainer();

    assertEquals(2, container.getPorts().length);

    for (ContainerPort containerPort : container.getPorts()) {
      assertEquals(containerPort.getPrivatePort(), portMapping.get(containerPort.getPublicPort()));
      portMapping.remove(containerPort.getPublicPort());
    }

    assertEquals(0, portMapping.size());
  }

  @Test
  @Category(IntegrationTest.class)
  public void testDockerWithEnvironmentVariables() {
    dockerContainer = new DockerContainer(testImage);

    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("envvarA", "valA");
    envVars.put("envvarB", "valB");

    dockerContainer.setEnvironmentVariables(envVars);
    dockerContainer.setName(containerName);
    dockerContainer.run();

    InspectContainerResponse inspectContainerResponse = dockerContainer.getInspectContainer();
    ContainerConfig config = inspectContainerResponse.getConfig();

    for (String env : config.getEnv()) {
      String[] split = env.split("=");

      if (envVars.get(split[0]) != null) {
        assertEquals(envVars.get(split[0]), split[1]);
        envVars.remove(split[0]);
      }
    }

    assertEquals(0, envVars.size());
  }
}