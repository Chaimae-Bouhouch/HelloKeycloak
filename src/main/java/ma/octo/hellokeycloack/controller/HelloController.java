package ma.octo.hellokeycloack.controller;

import ma.octo.hellokeycloack.dto.response.HelloResponse;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse("Hello there!");
    }

    @GetMapping("/hello/auth")
    public HelloResponse helloAuth() {
        return new HelloResponse("Hello, you are authenticated!");
    }

    @GetMapping("/hello/user")
    public HelloResponse helloUser() {
        return new HelloResponse("Hello user");
    }

    @GetMapping("/hello/admin")
    public HelloResponse helloAdmin() {
        return new HelloResponse("Hello admin");
    }

    @GetMapping("/hello/manager")
    public HelloResponse helloManager() {
        return new HelloResponse("Hello manager");
    }
}
