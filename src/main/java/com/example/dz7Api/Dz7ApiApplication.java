package com.example.dz7Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.dz7Api.repositories")
public class Dz7ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dz7ApiApplication.class, args);
	}

}
