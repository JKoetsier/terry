package nl.jkoetsier.uva.terry.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.Ports.Binding;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import nl.jkoetsier.uva.terry.docker.exception.ContainerInitException;
import nl.jkoetsier.uva.terry.docker.exception.NotExistingContainerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DockerContainer {

  private static Logger logger = LoggerFactory.getLogger(DockerContainer.class);

  // Max wait time in seconds
  private static Integer MAX_WAIT_FOR_INIT = 30;

  private String image;
  private String name;
  private String host;
  private HashMap<String, String> environmentVariables = new HashMap<>();
  private HashMap<Integer, Integer> portMapping = new HashMap<>();
  private HashMap<String, String> volumeMapping = new HashMap<>();

  private DockerClient dockerClient;
  private String containerId;
  private String readyLogLine;

  public DockerContainer(String image) {
    this();
    this.image = image;
  }

  public DockerContainer() {
    createClient();
  }

  public static DockerContainer fromExisting(String name) throws NotExistingContainerException {
    DockerContainer dockerContainer = new DockerContainer();
    dockerContainer.setName(name);

    List<Container> containers = dockerContainer.dockerClient.listContainersCmd().withShowAll(true)
        .withNameFilter(Arrays.asList(name)).exec();

    if (containers.size() == 0) {
      throw new NotExistingContainerException(String.format("Container '%s' does not exist", name));
    }

    Container container = containers.get(0);

    dockerContainer.containerId = container.getId();

    return dockerContainer;
  }

  public HashMap<String, String> getEnvironmentVariables() {
    return environmentVariables;
  }

  public HashMap<Integer, Integer> getPortMapping() {
    return portMapping;
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

  private void createClient() {
    if (host != null) {
      dockerClient = DockerClientBuilder.getInstance(host).build();
    } else {
      dockerClient = DockerClientBuilder.getInstance().build();
    }
  }

  public void run() {
    assert readyLogLine != null;

    logger.info("Spinning up docker container with image {}", image);

    CreateContainerCmd cmd = dockerClient.createContainerCmd(image);

    if (name != null) {
      logger.info("Setting docker container name as {}", name);
      cmd = cmd.withName(name);
    }

    if (portMapping.size() > 0) {
      cmd = setPortBindings(cmd);
    }

    if (environmentVariables.size() > 0) {
      cmd = setEnvs(cmd);
    }

    if (volumeMapping.size() > 0) {
      cmd = setVolumes(cmd);
    }

    CreateContainerResponse containerResponse = cmd.exec();
    containerId = containerResponse.getId();
    dockerClient.startContainerCmd(containerId).exec();

    Container container = getContainer();

    logger.info("Container state: {}", container.getState());

    assert container.getState().equals("running");
    final boolean[] containerInitialised = {false};

    LogContainerCmd logCmd = dockerClient.logContainerCmd(containerId).withStdOut(true)
        .withStdErr(true).withFollowStream(true);

    logCmd.exec(new LogContainerResultCallback() {

      @Override
      public void onNext(Frame item) {
        if (item.toString().contains(readyLogLine)) {
          containerInitialised[0] = true;

          try {
            close();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
    });

    Integer maxWait = MAX_WAIT_FOR_INIT * 1000;
    Integer interval = 500;
    Integer passed = 0;

    while (!containerInitialised[0]) {
      try {
        Thread.sleep(interval);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      passed += interval;

      if (passed > maxWait) {
        throw new ContainerInitException(
            "Container took too long to initialise. Check if ready log line is set correctly"
        );
      }
    }
  }

  private CreateContainerCmd setPortBindings(CreateContainerCmd cmd) {
    Ports portBindings = new Ports();
    List<ExposedPort> exposedPorts = new ArrayList<>();

    for (Entry<Integer, Integer> entry : portMapping.entrySet()) {
      ExposedPort exposedPort = ExposedPort.tcp(entry.getValue());
      portBindings.bind(exposedPort, Binding.bindPort(entry.getKey()));
      exposedPorts.add(exposedPort);

      logger.debug("Setting docker portmapping from {} to {}", entry.getKey(), entry.getValue());
    }

    return cmd.withExposedPorts(exposedPorts).withPortBindings(portBindings);
  }

  private CreateContainerCmd setEnvs(CreateContainerCmd cmd) {
    List<String> envs = new ArrayList<>();

    for (Entry<String, String> entry : environmentVariables.entrySet()) {
      String envString = String.format("%s=%s", entry.getKey(), entry.getValue());
      envs.add(envString);

      logger.debug("Setting docker environment variable {}", envString);
    }

    return cmd.withEnv(envs);
  }

  private CreateContainerCmd setVolumes(CreateContainerCmd cmd) {
    List<Volume> volumes = new ArrayList<>();
    List<Bind> binds = new ArrayList<>();

    for (Entry<String, String> entry : volumeMapping.entrySet()) {
      Volume volume = new Volume(entry.getValue());
      Bind bind = new Bind(entry.getKey(), volume);

      volumes.add(volume);
      binds.add(bind);

      logger.debug("Mapping host directory {} to container directory {}",
          entry.getKey(), entry.getValue()
      );
    }

    return cmd.withVolumes(volumes).withBinds(binds);
  }

  InspectContainerResponse getInspectContainer() {
    return dockerClient.inspectContainerCmd(containerId).exec();
  }

  Container getContainer() {
    return dockerClient.listContainersCmd().withShowAll(true)
        .withIdFilter(Arrays.asList(containerId)).exec().get(0);
  }

  public void stop() {
    if (containerId != null) {
      logger.info("Stopping docker container");

      try {
        dockerClient.stopContainerCmd(containerId).exec();
      } catch (NotModifiedException e) {
        // Happens when container already stopped
      }
    }
  }

  public void remove() {
    if (containerId != null) {

      try {
        dockerClient.removeContainerCmd(containerId).exec();
      } catch (NotModifiedException e) {
        // Happens when container already removed
      }
    }
  }

  boolean isRunning() {
    return getContainer().getState().equals("running");
  }

  public void addPortMapping(Integer publicPort, Integer internalPort) {
    this.portMapping.put(publicPort, internalPort);
  }

  public void addEnvironmentVariable(String key, String value) {
    this.environmentVariables.put(key, value);
  }

  public void addVolumeMapping(String hostLocation, String containerLocation) {
    this.volumeMapping.put(hostLocation, containerLocation);
  }

  /**
   * Accepts envvar as string (key=value)
   */
  public void addEnvironmentVariable(String envVarString) {
    String[] splitted = envVarString.split("=");

    if (splitted.length == 2) {
      addEnvironmentVariable(splitted[0], splitted[1]);
    }
  }

  public void addEnvironmentVariables(String[] envVarStrings) {
    for (String envVarString : envVarStrings) {
      addEnvironmentVariable(envVarString);
    }
  }

  public void setReadyLogLine(String dockerReadyLogLine) {
    this.readyLogLine = dockerReadyLogLine;
  }
}
