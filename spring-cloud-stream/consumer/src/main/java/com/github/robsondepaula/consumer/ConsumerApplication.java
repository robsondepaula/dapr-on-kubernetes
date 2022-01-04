package com.github.robsondepaula.consumer;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public  Consumer<Map<String, ?>> consumer() {
		return (map) -> {
			System.out.println("Spring Cloud Stream - Consumer called");
			System.out.println(map);
		};
	}
}
