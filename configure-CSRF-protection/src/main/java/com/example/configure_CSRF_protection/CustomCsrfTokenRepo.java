package com.example.configure_CSRF_protection;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomCsrfTokenRepo implements CsrfTokenRepository {
    private final JpaTokenRepo repo;
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        var uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN","_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        var identifier = request.getHeader("X-IDENTIFIER");
        var existingToken = repo.findByIdentifier(identifier);
        existingToken.ifPresentOrElse(x-> x.setToken(token.getToken()),()->{
            var newToken = new Token();
            newToken.setIdentifier(identifier);
            repo.save(newToken);
        });

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return repo.findByIdentifier(request.getHeader("X-IDENTIFIER"))
                .map(x -> new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", x.getToken()))
                .orElse(null);  // Return null if the token is not found
    }


}
