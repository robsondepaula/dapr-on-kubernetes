package com.github.robsondepaula.dapr.consumer;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @PostMapping("/consume")
    public void consume(@RequestBody Map<String, ?> message) {
        System.out.println("Dapr consumer called!");
        System.out.println(message);
    }
}
