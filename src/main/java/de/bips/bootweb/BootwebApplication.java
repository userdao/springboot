package de.bips.bootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootwebApplication {



  public static void main(String[] args) {
    SpringApplication.run(BootwebApplication.class, args);

    System.out.println("Main Application is starting!");

  }
}
