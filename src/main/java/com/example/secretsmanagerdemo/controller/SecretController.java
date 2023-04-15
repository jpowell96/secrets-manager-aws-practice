package com.example.secretsmanagerdemo.controller;

import com.example.secretsmanagerdemo.service.LocalSecretRetrievalService;
import com.example.secretsmanagerdemo.service.SecretRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {
    private final SecretRetrievalService secretRetrievalService;

    @Autowired
    public SecretController(SecretRetrievalService secretRetrievalService) {
        this.secretRetrievalService = secretRetrievalService;
    }

    @GetMapping("/secret")
    public String getSecret() {
        return secretRetrievalService.getSecret("my.secret");
    }
}
