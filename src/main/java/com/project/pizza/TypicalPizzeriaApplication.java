package com.project.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableJpaRepositories
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EnableJpaAuditing
public class TypicalPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypicalPizzeriaApplication.class, args);
	}

}
