package com.invillia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InvilliaServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvilliaServiceDiscoveryApplication.class, args);
	}

}