package com.example.ManagingUsers;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//#5
/*
    Add a filter after successful  auth
    logging its request header
 */
@Slf4j
public class AuthenticationLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var requestId = httpRequest.getHeader("Request-Id");
        log.info("SUCCESSFULLY AUTHENTICATED REQUEST WITH ID: {}", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
