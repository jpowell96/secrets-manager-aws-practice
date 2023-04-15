package com.example.secretsmanagerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClientBuilder;

@Configuration
public class AwsConfig {

    @Bean
    @Profile("prod")
    public SecretsManagerClient secretsManagerClient() {
        // https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-chain.html
        /**
         * AWS will first check for Credentials in the following order
         * 1. System Properties
         * 2. Environment Variables
         * 3. AWS Web Identity Token
         * 4. Check aws credentials and aws config files
         *
         * Once set up in AWS, will define in the ECS task definition
         * TODO: Remove hardcoded region
         * */
        return SecretsManagerClient.builder().region(Region.US_EAST_1)
                .build();
    }
}
