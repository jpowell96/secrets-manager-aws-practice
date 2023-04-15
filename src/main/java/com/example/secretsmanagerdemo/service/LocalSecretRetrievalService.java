package com.example.secretsmanagerdemo.service;

import com.example.secretsmanagerdemo.repository.LocalPropertySecretsRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod")
public class LocalSecretRetrievalService implements SecretRetrievalService {
    private final LocalPropertySecretsRetrievalRepository secretRetriever;

    @Autowired
    public LocalSecretRetrievalService(LocalPropertySecretsRetrievalRepository secretRetriever) {
        this.secretRetriever = secretRetriever;
    }

    @Override
    public String getSecret(String secretName) {
        return secretRetriever.getSecret(secretName);
    }
}
