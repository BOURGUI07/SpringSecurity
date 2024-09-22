package com.example.configure_CSRF_protection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTokenRepo extends JpaRepository<Token, Integer> {
    Optional<Token> findByIdentifier(String identifier);
}
