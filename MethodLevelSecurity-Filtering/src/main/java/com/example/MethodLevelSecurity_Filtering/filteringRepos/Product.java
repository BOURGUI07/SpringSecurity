package com.example.MethodLevelSecurity_Filtering.filteringRepos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String owner;
}
