package com.example.MethodLevelSecurity_Filtering.filteringRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @PostFilter("filterObject.owner == authentication.name")
    List<Product> findByNameContaining(String text);

    /*
        to select only the data we need add this as a bean in projectConfig
        @Bean
        public SecurityEvaluationContextextension securityEvaluationContextextension(){
            return  new SecurityEvaluationContextextension();
        }
        @Query("select p from Product p where p.name like %:text% AND
                p.owner= ?#{authentication.name}")
        List<Product> findByNameContaining(String text);
     */
}
