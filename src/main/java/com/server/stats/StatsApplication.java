package com.server.stats;

import com.server.stats.controller.StatsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Main class of the project that must remain an entry point with as little logic as possible.
 */

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class StatsApplication {

	private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

	/**
	 * Main method, entry point to our server
	 * @param args List of input arguments.
	 */
	public static void main(String[] args) {

		logger.info("Init the application...");
		SpringApplication.run(StatsApplication.class, args);
	}

}
