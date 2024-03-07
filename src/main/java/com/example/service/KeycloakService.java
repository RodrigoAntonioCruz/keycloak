package com.example.service;

import com.example.configuration.KeycloakPropertiesConfiguration;
import com.example.domain.UserRequest;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class KeycloakService {


    private final Keycloak keycloak;


    private final KeycloakPropertiesConfiguration properties;

    public void create(UserRequest request) {
        resource().users().create(build(request));
    }

    public void update(String id, UserRequest request) {
        resource().users().get(id).update(build(request));
    }

    public List<UserRepresentation> findByKeyword(String keyword) {
        var users = resource().users().search(keyword);
        if (users.isEmpty()) {
            users = resource().users().searchByAttributes(keyword);
        }
        return users;
    }

    public void delete(String userId) {
        resource().users().get(userId).remove();
    }


    public void roles() {

        //Lista todas as ROLES do cliente
        var rolesClient = resource().clients().get("98c6d912-36ff-4c26-98e6-2e7ab0a9926e").roles();

        //Atribuí uma lista de ROLES do cliente ao usuário especificado
        resource().users().get("8901c25f-1d64-4d3d-aafc-af403b941f41").roles()
                .clientLevel("98c6d912-36ff-4c26-98e6-2e7ab0a9926e").add(List.of(
                        rolesClient.list().get(1),
                        rolesClient.list().get(17)
                ));



    }


    private UserRepresentation build(UserRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(request.isEnabled());
        user.setEmailVerified(request.isEmailVerified());
        user.setRequiredActions(Collections.emptyList());
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setCredentials(Collections.singletonList(credentials(request.getPassword())));
        return user;
    }

    private CredentialRepresentation credentials(String password) {
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(password);
        return credentials;
    }

    private RealmResource resource() {
        return keycloak.realm(properties.getRealm());
    }
}
