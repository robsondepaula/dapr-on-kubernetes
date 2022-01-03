package com.github.robsondepaula.eurekasvc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaSvc2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSvc2Application.class, args);
	}

	@Bean
	@LoadBalanced
	Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	WebClient webClient(Builder builder) {
		return builder.build();
	}

}
