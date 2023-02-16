package com.ezra.loanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
public class LoanapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanapiApplication.class, args);
	}

}
