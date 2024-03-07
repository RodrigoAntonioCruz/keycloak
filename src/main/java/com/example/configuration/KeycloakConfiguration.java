package com.example.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Data
@Configuration
@AllArgsConstructor
public class KeycloakConfiguration {

    private static Keycloak keycloak;

    private final KeycloakPropertiesConfiguration properties;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getServer())
                .realm(properties.getRealm())
                .grantType(properties.getGrantType())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .build();
    }
}