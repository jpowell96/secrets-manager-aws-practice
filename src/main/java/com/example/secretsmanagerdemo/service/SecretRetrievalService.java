package com.example.secretsmanagerdemo.service;

import com.example.secretsmanagerdemo.repository.SecretRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretRetrievalService {
    private final SecretRetriever secretRetriever;

    @Autowired
    public SecretRetrievalService(SecretRetriever secretRetriever) {
        this.secretRetriever = secretRetriever;
    }

    public String getSecret(String secretName) {
        return secretRetriever.getSecret(secretName);
    }
}
