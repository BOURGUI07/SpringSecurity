package com.example.MethodLevelSecurity_Filtering.preFiltering;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner() == authentication.name")
    public List<Product> sell(List<Product> products) {
        return products;
    }
}
