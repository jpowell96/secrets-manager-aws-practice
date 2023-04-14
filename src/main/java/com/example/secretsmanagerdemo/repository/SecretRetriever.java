package com.example.secretsmanagerdemo.repository;

public interface SecretRetriever {
    String getSecret(String secretName);
}

