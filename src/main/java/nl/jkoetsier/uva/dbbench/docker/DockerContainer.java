package nl.jkoetsier.uva.dbbench.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.Ports.Binding;
import com.github.dockerjava.core.DockerClientBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DockerContainer {

  private static Logger logger = LoggerFactory.getLogger(DockerContainer.class);

  private String image;
  private String name;
  private String host;
  private HashMap<String, String> environmentVariables;
  private HashMap<Integer, Integer> portMapping;

  private DockerClient dockerClient;
  private String containerId;

  public DockerContainer(String image) {
    this.image = image;

    createClient();
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEnvironmentVariables(
      HashMap<String, String> environmentVariables) {
    this.environmentVariables = environmentVariables;
  }

  public void setPortMapping(HashMap<Integer, Integer> portMapping) {
    this.portMapping = portMapping;
  }

  private void createClient() {
    if (host != null) {
      dockerClient = DockerClientBuilder.getInstance(host).build();
    } else {
      dockerClient = DockerClientBuilder.getInstance().build();
    }
  }

  public void run() {
    logger.info("Spinning up docker container with image {}", image);

    CreateContainerCmd cmd = dockerClient.createContainerCmd(image);

    if (name != null) {
      logger.info("Setting docker container name as {}", name);
      cmd = cmd.withName(name);
    }

    if (portMapping != null) {
      cmd = setPortBindings(cmd);
    }

    if (environmentVariables != null) {
      cmd = setEnvs(cmd);
    }

    CreateContainerResponse containerResponse = cmd.exec();
    containerId = containerResponse.getId();
    dockerClient.startContainerCmd(containerId).exec();

    Container container = getContainer();
    assert container.getState().equals("running");

    logger.info("Container is running");
  }

  private CreateContainerCmd setPortBindings(CreateContainerCmd cmd) {
    Ports portBindings = new Ports();
    List<ExposedPort> exposedPorts = new ArrayList<>();

    for (Entry<Integer, Integer> entry : portMapping.entrySet()) {
      ExposedPort exposedPort = ExposedPort.tcp(entry.getValue());
      portBindings.bind(exposedPort, Binding.bindPort(entry.getKey()));
      exposedPorts.add(exposedPort);
    }

    return cmd.withExposedPorts(exposedPorts).withPortBindings(portBindings);
  }

  private CreateContainerCmd setEnvs(CreateContainerCmd cmd) {
    List<String> envs = new ArrayList<>();

    for (Entry<String, String> entry : environmentVariables.entrySet()) {
      envs.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
    }

    return cmd.withEnv(envs);
  }

  protected InspectContainerResponse getInspectContainer() {
    return dockerClient.inspectContainerCmd(containerId).exec();
  }

  protected Container getContainer() {
    return dockerClient.listContainersCmd().withIdFilter(Arrays.asList(containerId)).exec().get(0);
  }

  public void stop() {
    dockerClient.stopContainerCmd(containerId).exec();
  }

  public void remove() {
    dockerClient.removeContainerCmd(containerId).exec();
  }
}
