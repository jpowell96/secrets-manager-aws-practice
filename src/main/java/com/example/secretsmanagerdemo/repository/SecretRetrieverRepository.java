package com.example.secretsmanagerdemo.repository;

public interface SecretRetrieverRepository {
    String getSecret(String secretName);
}

