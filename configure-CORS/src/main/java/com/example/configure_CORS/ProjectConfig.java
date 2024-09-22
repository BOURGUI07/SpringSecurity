package com.example.configure_CORS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(c->{
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("example.com","example.org"));
                        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                        config.setAllowedHeaders(List.of("*"));
                        return config;
                    };
                    c.configurationSource(source);
                })
                .authorizeHttpRequests(x-> x.anyRequest().permitAll())
                .build();
    }
}
