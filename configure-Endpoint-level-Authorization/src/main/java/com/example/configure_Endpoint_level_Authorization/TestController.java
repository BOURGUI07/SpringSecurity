package com.example.configure_Endpoint_level_Authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postA() {
        return "Works!";
    }

    @GetMapping("/a")
    public String getA() {
        return "Works!";
    }

    @GetMapping("/a/b")
    public String getB() {
        return "Works!";
    }

    @GetMapping("/a/b/c")
    public String getC() {
        return "Works!";
    }
}
