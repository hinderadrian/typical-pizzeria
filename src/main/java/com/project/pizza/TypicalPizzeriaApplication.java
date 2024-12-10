package com.project.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TypicalPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypicalPizzeriaApplication.class, args);
	}

}
