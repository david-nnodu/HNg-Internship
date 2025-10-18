package com.example.hngstage0.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.time.Instant;
import java.util.*;

@RestController
public class ProfileController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getProfile() {
        Map<String, Object> response = new HashMap<>();

        // Initialize RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String catFact = "Cats are awesome, even when APIs fail!";

        try {
            // Fetch cat fact
            Map<?, ?> catResponse = restTemplate.getForObject("https://catfact.ninja/fact", Map.class);
            if (catResponse != null && catResponse.get("fact") != null) {
                catFact = catResponse.get("fact").toString();
            }
        } catch (Exception e) {
            System.err.println("Failed to fetch cat fact: " + e.getMessage());
        }

        // Build response
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("email", "nnodud@gmail.com"); //
        user.put("name", "Nnodu David");
        user.put("stack", "Java/Spring Boot");

        response.put("status", "success");
        response.put("user", user);
        response.put("timestamp", Instant.now().toString()); // UTC ISO 8601
        response.put("fact", catFact);

        return ResponseEntity.ok(response);
    }
}
