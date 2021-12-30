package com.github.robsondepaula.svc1;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of("message", "Hello, from svc1.");
    }
}
