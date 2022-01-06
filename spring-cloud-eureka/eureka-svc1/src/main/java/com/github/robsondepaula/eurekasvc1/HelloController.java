package com.github.robsondepaula.eurekasvc1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${eureka.instance.metadataMap.instanceId}")
    private String instanceId;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of("message",
                "Hello, from " + instanceId + ".");
    }
}