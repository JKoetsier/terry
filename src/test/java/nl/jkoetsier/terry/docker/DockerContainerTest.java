package nl.jkoetsier.terry.docker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerConfig;
import com.github.dockerjava.api.model.ContainerPort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.terry.docker.exception.NotExistingContainerException;
import nl.jkoetsier.terry.testclass.IntegrationTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DockerContainerTest {

  private static String containerName = "testcontainer92824912240djkld";
  private String testImage = "hello-world";
  private String readyLogLine = "Hello from Docker!";
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
    dockerContainer.setReadyLogLine(readyLogLine);
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
    dockerContainer.setReadyLogLine(readyLogLine);

    HashMap<Integer, Integer> portMapping = new HashMap<>();
    portMapping.put(3500, 3700);
    portMapping.put(3600, 3800);

    for (Entry<Integer, Integer> entry : portMapping.entrySet()) {
      dockerContainer.addPortMapping(entry.getKey(), entry.getValue());
    }

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
    dockerContainer.setReadyLogLine(readyLogLine);

    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("envvarA", "valA");
    envVars.put("envvarB", "valB");

    for (Entry<String, String> entry : envVars.entrySet()) {
      dockerContainer.addEnvironmentVariable(entry.getKey(), entry.getValue());
    }

    dockerContainer.setName(containerName);
    dockerContainer.run();

    checkEnvironmentVariables(envVars, dockerContainer);
  }

  private void checkEnvironmentVariables(HashMap<String, String> expected,
      DockerContainer dockerContainer) {
    InspectContainerResponse inspectContainerResponse = dockerContainer.getInspectContainer();
    ContainerConfig config = inspectContainerResponse.getConfig();

    for (String env : config.getEnv()) {
      String[] split = env.split("=");

      if (expected.get(split[0]) != null) {
        assertEquals(expected.get(split[0]), split[1]);
        expected.remove(split[0]);
      }
    }

    assertEquals(0, expected.size());
  }

  @Test
  @Category(IntegrationTest.class)
  public void testEnvironmentVariablesAsStrings() {
    dockerContainer = new DockerContainer(testImage);
    dockerContainer.setReadyLogLine(readyLogLine);

    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("envvarA", "valA");
    envVars.put("envvarB", "valB");

    for (Entry<String, String> entry : envVars.entrySet()) {
      dockerContainer
          .addEnvironmentVariable(String.format("%s=%s", entry.getKey(), entry.getValue()));
    }

    assertEquals(envVars, dockerContainer.getEnvironmentVariables());
  }


  @Test
  @Category(IntegrationTest.class)
  public void testEnvironmentVariablesAsListOfStrings() {
    dockerContainer = new DockerContainer(testImage);
    dockerContainer.setReadyLogLine(readyLogLine);

    HashMap<String, String> envVars = new HashMap<>();
    envVars.put("envvarA", "valA");
    envVars.put("envvarB", "valB");

    List<String> envVarStrings = new ArrayList<>();

    for (Entry<String, String> entry : envVars.entrySet()) {

      envVarStrings.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
    }

    dockerContainer.addEnvironmentVariables(envVarStrings.toArray(new String[0]));

    assertEquals(envVars, dockerContainer.getEnvironmentVariables());
  }
}