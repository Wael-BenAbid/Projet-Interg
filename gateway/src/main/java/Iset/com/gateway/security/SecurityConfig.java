package Iset.com.gateway.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@KeycloakConfiguration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())  // Désactiver la protection CSRF
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/eureka/**").permitAll()  // Permettre l'accès à Eureka
                                .anyRequest().authenticated()  // Authentification requise pour toutes les autres requêtes
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder())));  // Configurer la validation du JWT

        return httpSecurity.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Configure le décodeur JWT avec l'URL de l'issuer (Keycloak)
        return JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/micro-services");
    }
}
