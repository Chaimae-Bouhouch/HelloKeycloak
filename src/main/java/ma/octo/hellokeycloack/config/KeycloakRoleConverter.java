package ma.octo.hellokeycloack.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
   private final String rolePrefix = "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        System.out.println("Hello");
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> authorities = ((List<String>)realmAccess.getOrDefault("roles", new ArrayList<>()))
                .stream()
                .map(roleName -> String.format("%s%s", rolePrefix, roleName).toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }
}
