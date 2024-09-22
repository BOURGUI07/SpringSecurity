package com.example.configure_CSRF_protection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

        @GetMapping("/hello")
    public String hello() {
        return "GET Hello";
    }
    @PostMapping("/hello")
    public String hello1() {
        return "POST Hello";
    }




}
