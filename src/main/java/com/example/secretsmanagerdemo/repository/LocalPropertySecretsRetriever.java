package com.example.secretsmanagerdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class LocalPropertySecretsRetriever implements SecretRetriever {
    private final Environment environment;

    @Autowired
    public LocalPropertySecretsRetriever(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getSecret(String secretName) {
        String value =  environment.getProperty(secretName, String.class);
        // TODO: Throw an exception if value is null
        return value;
    }
}
