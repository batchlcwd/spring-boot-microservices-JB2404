package com.docker.exmple.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    public String test() {
        return "test this apis, Its working";
    }

    @GetMapping("/detail")
    public Map<String, String> getDetail() {
        return Map.of("projectName", "Docker Demo Application", "Version", "1.0V");
    }

}
