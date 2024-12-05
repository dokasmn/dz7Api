package com.example.dz7Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.dz7Api")
public class Dz7ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dz7ApiApplication.class, args);
		System.out.println("Running...");
	}

}