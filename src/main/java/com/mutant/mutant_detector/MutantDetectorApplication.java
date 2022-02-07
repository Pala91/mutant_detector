package com.mutant.mutant_detector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MutantDetectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantDetectorApplication.class, args);
	}

}
