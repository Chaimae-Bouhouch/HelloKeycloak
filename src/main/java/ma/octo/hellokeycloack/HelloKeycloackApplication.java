package ma.octo.hellokeycloack;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class HelloKeycloackApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloKeycloackApplication.class, args);

    }

}
