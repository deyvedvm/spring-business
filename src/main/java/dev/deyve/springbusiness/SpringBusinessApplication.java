package dev.deyve.springbusiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBusinessApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBusinessApplication.class, args);
        logger.info("Running Spring Business Application!");
        logger.info("Running on java: " + System.getProperty("java.version"));
    }

}
