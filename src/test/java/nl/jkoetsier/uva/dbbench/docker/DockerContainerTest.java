package nl.jkoetsier.uva.dbbench.docker;

import static org.junit.Assert.*;

import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerConfig;
import com.github.dockerjava.api.model.ContainerPort;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nl.jkoetsier.uva.dbbench.testclass.IntegrationTest;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Ignore
// ignore for now. should be able to run separately from maven
public class DockerContainerTest {
  private String testImage = "hello-world";

  private DockerContainer dockerContainer;

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
    dockerContainer.setName("testcontainer");
    dockerContainer.run();

    assertEquals("running", dockerContainer.getContainer().getState());

    List<String> names = Arrays.asList(dockerContainer.getContainer().getNames());
    assertTrue(names.contains("/testcontainer"));
  }

  @Test
  @Category(IntegrationTest.class)
  public void testDockerRunWithPorts() {
    dockerContainer = new DockerContainer(testImage);

    HashMap<Integer, Integer> portMapping = new HashMap<>();
    portMapping.put(3500, 3700);
    portMapping.put(3600, 3800);
    dockerContainer.setPortMapping(portMapping);

    dockerContainer.setName("testcontainer");
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
    dockerContainer.setName("testcontainer");
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