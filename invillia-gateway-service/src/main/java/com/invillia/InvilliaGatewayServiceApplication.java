package com.invillia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class InvilliaGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvilliaGatewayServiceApplication.class, args);
	}

}
