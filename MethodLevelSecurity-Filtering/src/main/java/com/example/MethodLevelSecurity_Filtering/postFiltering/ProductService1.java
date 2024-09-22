package com.example.MethodLevelSecurity_Filtering.postFiltering;

import com.example.MethodLevelSecurity_Filtering.preFiltering.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService1 {


    @PostFilter("filterObject.owner() == authentication.name")
    public List<Product> getProducts() {
         List<Product> products = new ArrayList<>();
         Product product1 = new Product("product1","john");
         Product product2 = new Product("product2","john");
         Product product3 = new Product("product3","jane");
         Product product4 = new Product("product4","jane");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        return products;

    }
}
