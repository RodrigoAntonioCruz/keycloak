package com.example.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakPropertiesConfiguration {

    private String server;
    private String realm;
    private String username;
    private String password;
    private String clientId;
    private String grantType;
    private String contentType;
    private String clientSecret;

}