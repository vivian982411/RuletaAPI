package com.ibm.academia.apirest.RuletaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RuletaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuletaApiApplication.class, args);
	}

}
