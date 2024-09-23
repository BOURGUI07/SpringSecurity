package com.example.OAuth2_Resource_Server;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    @Value("${keySetURI}")
    private String keySetURI;
    @Value("${introspectionUri}")
    private String introspectionUri;
    @Value("${resourceserver.clientID}")
    private String resourceServerClientID;
    @Value("${resourceserver.secret}")
    private String resourceServerClientSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
            For Non-Opaque Token (JWT tokens)
            http.oauth2ResourceServer(
                c-> c.jwt(
                        j-> j.jwkSetUri(keySetURI)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                )
            );
         */
        /*
            // For Opaque token
        http.oauth2ResourceServer(
                x-> x.opaqueToken(
                        o-> o.introspectionUri(introspectionUri)
                                .introspectionClientCredentials(resourceServerClientID, resourceServerClientSecret)
                )
            );
         */
        /*
            // Now the resource server works with two authorization servers running on ports
            // 8080 and 7070
            http.oauth2ResourceServer(
                j->j.authenticationManagerResolver(authenticationManagerResolver())
            );
         */

        http.oauth2ResourceServer(
                j->j.authenticationManagerResolver(
                        authenticationManagerResolver(jwtDecoder(),introspector())
                )
        );
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated());
        return http.build();
    }

    /*
        If I wanted to work with two authorization servers
        @Bean
        public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
            return JwtIssuerAuthenticationManagerResolver
                .fromTrustedIssuers("http://localhost:8080","http://localhost:8080");

        }
     */

    // If I wanted to work with two authorization servers
    // one is opaque and the other is jwt
    // if the client sends type=jwt, then the resource server validates
    // the token using the authorization server with JWT
    // otherwise it uses the authorization server with opaque token

    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver(
            JwtDecoder jwtDecoder,
            OpaqueTokenIntrospector introspector
    ) {
        var jwtAuth = new ProviderManager(new JwtAuthenticationProvider(jwtDecoder));
        var opaqueAuth = new ProviderManager(new OpaqueTokenAuthenticationProvider(introspector));

        return (request) -> {
            if("jwt".equals(request.getHeader("type"))) {
                return jwtAuth;
            }else{
                return opaqueAuth;
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri("http://localhost:7070/oauth2/jwks")
                .build();
    }

    @Bean
    public OpaqueTokenIntrospector introspector() {
        return new SpringOpaqueTokenIntrospector(
                "http://localhost:6060/oauth2/introspect",
                "client","secret"
        );
    }
}
