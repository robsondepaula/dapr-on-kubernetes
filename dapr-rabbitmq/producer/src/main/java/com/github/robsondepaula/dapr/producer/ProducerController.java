package com.github.robsondepaula.dapr.producer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ProducerController {
    @Value("${baseUrl}")
    private String baseUrl;

    private WebClient client = WebClient.create();

    @PostMapping("/produce")
    public void produce(@RequestBody Map<String, ?> message) {
        client.post()
                .uri(baseUrl + "publish/rabbitmq/ardp-topic")
                .bodyValue(message)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
