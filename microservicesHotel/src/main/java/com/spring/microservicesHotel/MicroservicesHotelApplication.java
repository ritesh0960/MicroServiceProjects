package com.spring.microservicesHotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesHotelApplication.class, args);
	}

}
