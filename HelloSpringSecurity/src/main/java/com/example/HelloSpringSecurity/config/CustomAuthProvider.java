package com.example.HelloSpringSecurity.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.AuthProvider;
import java.util.ArrayList;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Authentication logic here
        var username = authentication.getName();
        var password = authentication.getCredentials().toString();
        if("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }else{
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
        /*
            Now you replaced the responsibilities of userdetailsservice and password
            encoder by auth provider, in projectConfig, you're NOT allowed to use the
            two Beans!
         */
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //type of the authentication implementation here
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
