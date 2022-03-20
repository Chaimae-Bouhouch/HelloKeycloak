package ma.octo.hellokeycloack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // Manage cors
        http.cors().configurationSource(corsConfigurationSource());

        // Disable session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Disable csrf
        http.csrf().disable();

        // Authorize requests
        http
            .authorizeRequests()
            .antMatchers("/hello/auth").authenticated()
            .antMatchers("/hello/user").hasAnyRole("USER")
            .antMatchers("/hello/manager").hasAnyRole("MANAGER")
            .antMatchers("/hello/admin").hasAnyRole("ADMIN")
            .antMatchers("/hello/edit").hasAuthority("EDIT")
//            .antMatchers("/hello/delete").hasAnyAuthority("DELETE")
            .anyRequest().permitAll();

        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
    }

    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("*"));
            config.setAllowedMethods(List.of("GET", "POST", "OPTIONS"));
            config.setAllowCredentials(true);
            config.setAllowedHeaders(List.of("*"));
            config.setMaxAge(3600L);

            return config;
        };
    }
}
