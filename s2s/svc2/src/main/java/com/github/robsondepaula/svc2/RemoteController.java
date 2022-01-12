package com.github.robsondepaula.svc2;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class RemoteController {

    @Value("${baseUrl}")
    private String baseUrl;
    
    private WebClient client = WebClient.create();

    @GetMapping("/remote-hello")
    public Map<?, ?> remoteHello() {
        return client.get()
                .uri(baseUrl + "/hello")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
