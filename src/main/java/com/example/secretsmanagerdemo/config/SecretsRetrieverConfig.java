package com.example.secretsmanagerdemo.config;

import com.example.secretsmanagerdemo.repository.AwsSecretsManagerSecretRetrievalRepositoryRepository;
import com.example.secretsmanagerdemo.repository.LocalPropertySecretsRetrievalRepository;
import com.example.secretsmanagerdemo.repository.SecretRetrieverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class SecretsRetrieverConfig {
    @Autowired
    Environment environment;

    @Bean
    public LocalPropertySecretsRetrievalRepository localSecretsRetriever(Environment environment) {
        return new LocalPropertySecretsRetrievalRepository(environment);
    }

    @Bean
    @Profile("prod")
    public AwsSecretsManagerSecretRetrievalRepositoryRepository awsSecretRetriever(SecretsManagerClient secretsManagerClient) {
        return new AwsSecretsManagerSecretRetrievalRepositoryRepository(secretsManagerClient);
    }
}
