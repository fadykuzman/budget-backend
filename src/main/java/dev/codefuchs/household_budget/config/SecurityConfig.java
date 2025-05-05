package dev.codefuchs.household_budget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable CSRF as we're using stateless JWT authentication
        http.csrf(AbstractHttpConfigurer::disable);

        // Set session management to stateless
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Set permissions on endpoints
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher("/unauthenticated")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/oauth2/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs*/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/api/**")).permitAll()
                .anyRequest().authenticated()
        );

        // Configure JWT resource server
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter())
                )
        );

        return http.build();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> keycloakJwtAuthenticationConverter() {
        return new KeycloakJwtAuthenticationConverter();
    }

    // Use a named class instead of lambdas to ensure type information is retained
    public static class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
        @Override
        public AbstractAuthenticationToken convert(Jwt jwt) {
            JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
            jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtGrantedAuthoritiesConverter());
            return jwtConverter.convert(jwt);
        }
    }

    public static class KeycloakJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();
            Collection<GrantedAuthority> defaultAuthorities = defaultConverter.convert(jwt);

            if (defaultAuthorities == null) {
                defaultAuthorities = Collections.emptyList();
            }

            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            String clientId = jwt.getClaim("azp");

            Collection<GrantedAuthority> clientRoles = extractKeycloakRoles(resourceAccess, clientId);
            Collection<GrantedAuthority> realmRoles = extractRealmRoles(realmAccess);

            return Stream.of(defaultAuthorities, clientRoles, realmRoles)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

        private Collection<GrantedAuthority> extractRealmRoles(Map<String, Object> realmAccess) {
            return extractRoles(realmAccess, "roles", "ROLE_");
        }

        private Collection<GrantedAuthority> extractKeycloakRoles(Map<String, Object> resourceAccess, String clientId) {
            if (resourceAccess == null || clientId == null) {
                return Collections.emptyList();
            }

            Map<String, Object> clientResource = (Map<String, Object>) resourceAccess.get(clientId);
            return extractRoles(clientResource, "roles", "ROLE_");
        }

        @SuppressWarnings("unchecked")
        private Collection<GrantedAuthority> extractRoles(Map<String, Object> rolesObject, String rolesKey, String prefix) {
            if (rolesObject == null) {
                return Collections.emptyList();
            }

            List<String> roles = (List<String>) rolesObject.get(rolesKey);
            if (roles == null) {
                return Collections.emptyList();
            }

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(prefix + role))
                    .collect(Collectors.toList());
        }
    }
}
