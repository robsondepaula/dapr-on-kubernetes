package com.github.robsondepaula.eurekasvc2;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class RemoteController {

    private WebClient client;

    public RemoteController(WebClient webClient) {
        client = webClient;
    }

    @GetMapping("/remote-hello")
    public Map<?, ?> remoteHello() {
        return client.get()
                .uri("http://eureka-svc1/hello")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
