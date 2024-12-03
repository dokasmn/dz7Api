package com.example.dz7api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.dz7Api")
public class Dz7ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dz7ApiApplication.class, args);
		System.out.println("Running...");
	}

}
