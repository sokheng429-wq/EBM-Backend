package com.example.EBM.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "status", "running",
                "usersEndpoint", "/api/users",
                "auth", "Use HTTP Basic auth for protected API routes"
        );
    }
}
