package com.ops.base.education.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EducationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationalApplication.class, args);
	}
}
