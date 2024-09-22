package com.example.configure_CSRF_protection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /*
    @PostMapping("/hello")
    public String hello() {
        return "POST Hello";
    }

     */

    @PostMapping("/ciao")
    public String ciao() {
        return "POST Ciao";
    }
}
