package com.example.MethodLevelSecurity_Filtering.filteringRepos;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController2 {
    private final ProductRepo productRepo;
    @GetMapping("/all/{text}")
    public List<Product> getAllProducts(@PathVariable String text) {
        return productRepo.findByNameContaining(text);
    }
}
