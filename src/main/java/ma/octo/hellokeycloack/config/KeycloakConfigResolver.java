package ma.octo.hellokeycloack.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfigResolver {

    @Bean
    public KeycloakSpringBootConfigResolver keycloakspringBootConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }
}
