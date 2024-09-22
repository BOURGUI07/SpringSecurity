package com.example.configure_CSRF_protection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    private Integer id;
    private String identifier;
    private String token;
}
