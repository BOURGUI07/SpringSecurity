package com.example.MethodLevelSecurity_Filtering.preFiltering;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/sell")
    public List<Product> sell() {
        // the preFilter() needs mutable parameters, else it will throw an exception!
        // that's we used new ArrayList instead of List.of()!!
        // if we use List.of() the prefilter gonna throw an exception
        // since he changes the parameters, expects them to be mutable
        var products = new ArrayList<Product>();
        products.add(new Product("product1","john"));
        products.add(new Product("product2","john"));
        products.add(new Product("product3","jane"));
        products.add(new Product("product4","jane"));
        return productService.sell(products);
    }
}
