package com.example.configure_CSRF_protection;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    private final CustomCsrfTokenRepo repo;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
            http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().permitAll());
         */
        /*
            http.formLogin(c->c.defaultSuccessUrl("/main",true))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated());
         */
        /*
            http.csrf(c->c.ignoringRequestMatchers("/ciao"))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().permitAll());
         */
        http.csrf(s->s.csrfTokenRepository(repo)
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()));
        http.authorizeHttpRequests(r-> r.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user = User.withUsername("mary")
                .password("12345")
                .roles("reader")
                .build();
        manager.createUser(user);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
