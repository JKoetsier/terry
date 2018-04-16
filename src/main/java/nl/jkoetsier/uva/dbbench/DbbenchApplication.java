package nl.jkoetsier.uva.dbbench;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbbenchApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(DbbenchApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Here we are");

  }
}
