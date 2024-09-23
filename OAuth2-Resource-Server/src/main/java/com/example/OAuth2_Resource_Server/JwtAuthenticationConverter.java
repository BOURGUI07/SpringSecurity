package com.example.OAuth2_Resource_Server;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt,CustomAuthentication> {
    @Override
    public CustomAuthentication convert(Jwt source) {
        List<GrantedAuthority> authorities = List.of(() -> "read");
        var priority = String.valueOf(source.getClaims().get("priority"));
        return new CustomAuthentication(source,authorities,priority);
    }
}
