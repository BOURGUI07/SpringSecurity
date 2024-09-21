package com.example.ManagingUsers;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

//#6
/*
    to be authenticated, the user must add the correct value of the static key
    for all requests in the 'Authorization' header
 */
@Component //to allow us to inject values from the properties file
public class StaticKeyAuthenticationFilter implements Filter {
    @Value("${authorization.key}")
    private String authenticationKey;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        var authentication = request.getHeader("Authorization");
        if(authentication.equals(authenticationKey)) {
            filterChain.doFilter(request, response);
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
