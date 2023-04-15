package com.example.secretsmanagerdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class AwsSecretsManagerSecretRetrievalRepositoryRepository implements SecretRetrieverRepository {
    private final SecretsManagerClient secretsManagerClient;

    @Autowired
    public AwsSecretsManagerSecretRetrievalRepositoryRepository(SecretsManagerClient secretsManagerClient) {
        this.secretsManagerClient = secretsManagerClient;
    }

    @Override
    public String getSecret(String secretName) {

        try {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse valueResponse = secretsManagerClient.getSecretValue(valueRequest);
            String secret = valueResponse.secretString();
            return secret;
        } catch (SecretsManagerException e) {
            return "The Secret Does not Exist";
        }
    }
}
