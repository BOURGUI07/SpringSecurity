package com.example.configure_CSRF_protection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/main")
    public String main() {
        return "main.html";
    }
}
