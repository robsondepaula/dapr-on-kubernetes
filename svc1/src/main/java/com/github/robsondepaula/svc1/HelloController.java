package com.github.robsondepaula.svc1;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public Map<String, Object> message() {
        return Map.of("message", "Hello, from Spring!");
    }
}
