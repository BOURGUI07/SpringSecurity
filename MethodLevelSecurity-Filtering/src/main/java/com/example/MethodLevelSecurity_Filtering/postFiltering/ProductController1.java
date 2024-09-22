package com.example.MethodLevelSecurity_Filtering.postFiltering;

import com.example.MethodLevelSecurity_Filtering.preFiltering.Product;
import com.example.MethodLevelSecurity_Filtering.preFiltering.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController1 {
    private final ProductService1 productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
