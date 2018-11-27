package de.bips.bootweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootwebApplication {

  private static final Logger logger = LoggerFactory.getLogger(BootwebApplication.class);


  public static void main(String[] args) {
    SpringApplication.run(BootwebApplication.class, args);

    logger.info("BootwebApplication is starting!");

  }
}
