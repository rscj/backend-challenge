package com.invillia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.invillia.repositories")
@SpringBootApplication
public class InvilliaStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvilliaStoreServiceApplication.class, args);
	}
}