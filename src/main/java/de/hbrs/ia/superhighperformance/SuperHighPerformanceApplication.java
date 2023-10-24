package de.hbrs.ia.superhighperformance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperHighPerformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHighPerformanceApplication.class, args);
	}

	@Bean
	public CommandLineRunner comandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Started");
		};
	}
}
