package com.company.paragraphanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ParagraphanalyticsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphanalyticsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParagraphanalyticsApplication.class, args);
		LOGGER.debug("Simple log statement with inputs {}, {} and {}", 1,2,3);
	}

}
