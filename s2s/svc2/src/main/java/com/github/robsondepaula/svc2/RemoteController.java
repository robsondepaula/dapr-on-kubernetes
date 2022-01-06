package com.github.robsondepaula.svc2;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class RemoteController {

    private WebClient client = WebClient.create("http://localhost:8080");

    @GetMapping("/remote-hello")
    public Map<?, ?> remoteHello() {
        return client.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
