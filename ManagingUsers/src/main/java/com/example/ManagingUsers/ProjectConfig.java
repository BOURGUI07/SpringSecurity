package com.example.ManagingUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.util.HashMap;

//#3
@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Explicit column selection instead of *
        var usersByUsernameQuery = "select username, password, enabled from manageusers.usertable where username = ?";
        var authsByUsernameQuery = "select username, authority from manageusers.authoritytable where username = ?";

        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUsernameQuery);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        var encoders = new HashMap<String,PasswordEncoder>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
             //   .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
             //   .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(r->r.anyRequest().permitAll());
        return http.build();
    }
}

