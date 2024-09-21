package com.example.configure_Endpoint_level_Authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/products/{code}")
    public String getProduct(@PathVariable String code) {
        return  code;
    }
}
