package com.example.OAuth2_Resource_Server;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public Authentication demo(Authentication auth) {
        return auth;
    }
}
