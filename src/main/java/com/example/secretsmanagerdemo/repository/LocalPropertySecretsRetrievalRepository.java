package com.example.secretsmanagerdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class LocalPropertySecretsRetrievalRepository implements SecretRetrieverRepository {
    private final Environment environment;

    @Autowired
    public LocalPropertySecretsRetrievalRepository(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getSecret(String secretName) {
        String value =  environment.getProperty(secretName, String.class);
        // TODO: Throw an exception if value is null
        return value;
    }
}
