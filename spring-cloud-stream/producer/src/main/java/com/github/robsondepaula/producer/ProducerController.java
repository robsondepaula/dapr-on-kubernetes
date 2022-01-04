package com.github.robsondepaula.producer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("/publish")
    public void publish(@RequestBody Map<String, ?> message) {
        streamBridge.send("producer-out-0", message);
    }
}
