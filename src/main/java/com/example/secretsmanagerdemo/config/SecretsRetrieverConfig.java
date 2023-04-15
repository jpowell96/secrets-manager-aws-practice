package com.example.secretsmanagerdemo.config;

import com.example.secretsmanagerdemo.repository.AwsSecretsManagerSecretRetriever;
import com.example.secretsmanagerdemo.repository.CompositeSecretsRetriever;
import com.example.secretsmanagerdemo.repository.LocalPropertySecretsRetriever;
import com.example.secretsmanagerdemo.repository.SecretRetriever;
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
    @Profile("default")
    public SecretRetriever localSecretsRetriever(Environment environment) {
        return new LocalPropertySecretsRetriever(environment);
    }

    @Bean
    public SecretRetriever awsSecretsRetriever(SecretsManagerClient secretsManagerClient) {
        return new AwsSecretsManagerSecretRetriever(secretsManagerClient);
    }

    @Bean
    @Profile("prod")
    public SecretRetriever compositeSecretRetriever(LocalPropertySecretsRetriever localPropertySecretsRetriever, AwsSecretsManagerSecretRetriever awsSecretsManagerSecretRetriever) {
        return new CompositeSecretsRetriever(awsSecretsManagerSecretRetriever, localPropertySecretsRetriever);
    }
}
