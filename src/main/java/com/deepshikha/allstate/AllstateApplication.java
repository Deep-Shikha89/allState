package com.deepshikha.allstate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AllstateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllstateApplication.class, args);
	}

}
