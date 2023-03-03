package com.example.airbnbpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirbnbPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbPracticeApplication.class, args);
	}

}
