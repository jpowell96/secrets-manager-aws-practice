package com.example.secretsmanagerdemo.service;

import com.example.secretsmanagerdemo.repository.AwsSecretsManagerSecretRetrievalRepositoryRepository;
import com.example.secretsmanagerdemo.repository.LocalPropertySecretsRetrievalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CompositeSecretRetrievalService implements SecretRetrievalService {
    private final AwsSecretsManagerSecretRetrievalRepositoryRepository awsSecretsManagerSecretRetrievalRepository;
    private final LocalPropertySecretsRetrievalRepository localPropertySecretsRetrievalRepository;

    @Autowired
    public CompositeSecretRetrievalService(AwsSecretsManagerSecretRetrievalRepositoryRepository awsSecretsManagerSecretRetrievalRepository, LocalPropertySecretsRetrievalRepository localPropertySecretsRetrievalRepository) {
        this.awsSecretsManagerSecretRetrievalRepository = awsSecretsManagerSecretRetrievalRepository;
        this.localPropertySecretsRetrievalRepository = localPropertySecretsRetrievalRepository;
    }

    @Override
    public String getSecret(String secretName) {
        String secret = localPropertySecretsRetrievalRepository.getSecret(secretName);

        if (secret != null) {
            return secret;
        }

        return awsSecretsManagerSecretRetrievalRepository.getSecret(secretName);
    }
}
