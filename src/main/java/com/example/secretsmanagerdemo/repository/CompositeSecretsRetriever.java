package com.example.secretsmanagerdemo.repository;

/**
 * Checks the local environment properties for a secret,
 * and then checks AWS Secrets Manager if it cannot find it.
 *
 * */
public class CompositeSecretsRetriever implements SecretRetriever {
    private final AwsSecretsManagerSecretRetriever awsSecretRetriever;
    private final LocalPropertySecretsRetriever localPropertySecretsRetriever;

    public CompositeSecretsRetriever(AwsSecretsManagerSecretRetriever awsSecretRetriever, LocalPropertySecretsRetriever localPropertySecretsRetriever) {
        this.awsSecretRetriever = awsSecretRetriever;
        this.localPropertySecretsRetriever = localPropertySecretsRetriever;
    }

    @Override
    public String getSecret(String secretName) {
        // 1. Try getting it locally
        String secret = localPropertySecretsRetriever.getSecret(secretName);

        // 2. If not found locally, check AWS
        if (secret == null) {
            return awsSecretRetriever.getSecret(secretName);
        } else {
            return secret;
        }
    }
}
