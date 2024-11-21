package com.csit321g2.Capstone;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapstoneApplication {

	static Logger logger = Logger.getLogger(CapstoneApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
		
		logger.info("System is up and running!");
	}

}
