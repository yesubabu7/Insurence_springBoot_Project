package com.example.Insurence_SpringBoot.yesu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "yesu.controllers", "yesu.models", "yesu.repositery", "yesu.rowMappers",
		"yesu.services", "yesu.contracts" })
public class InsurenceSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurenceSpringBootApplication.class, args);
	}

}
