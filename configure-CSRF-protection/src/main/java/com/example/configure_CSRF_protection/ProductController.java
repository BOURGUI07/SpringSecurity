package com.example.configure_CSRF_protection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/products")
@Slf4j
public class ProductController {
    @PostMapping("/add")
    public String addProduct(@RequestParam String name) {
        log.info("ADDING PRODUCT: {}", name);
        return "main.html";
    }
}
