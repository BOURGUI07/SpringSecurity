package com.example.configure_CORS;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MvcController {
    @GetMapping("/")
    public String main(){
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin("http://localhost:8080")
    public String test(){
        log.info("TEST METHOD CALLED");
        return "HELLO WORLD";
    }
}
