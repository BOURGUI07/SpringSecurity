package com.example.ManagingUsers;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//#4
/*
    we will check whether the request-Id header exists, if it does, we forward the request
    to the next filter in the chain by calling doFilter() method.
    If the header doesn't exist, we send a 400 bad request on the response without
    forwarding it to the next filter.
    In the security filter chain, we gonna use the addFilterBefore() method of http security.
 */
public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        var requestId = httpRequest.getHeader("Request-Id");
        if(requestId==null ||requestId.isBlank()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
        /*
            now to access the endpoint:
            Invoke-WebRequest -Uri "http://localhost:8080/hello" -Headers @{ "Request-Id" = "some-unique-id" }
         */
    }
}
