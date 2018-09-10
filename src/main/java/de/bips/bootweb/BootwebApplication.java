package de.bips.bootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootwebApplication {



  public static void main(String[] args) {
    SpringApplication.run(BootwebApplication.class, args);

    System.out.println("Main Application is starting!");


    System.out.println("Elves is in the home");

  }


  public static void help() {

  }
}
