package com.example.HelloSpringSecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    private final CustomAuthProvider customAuthProvider;

    /*@Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
            Use The HTTP Basic as Auth Method
         */
        http.httpBasic(Customizer.withDefaults());
        /*
            how to authorize the requests received on specific endpoints
         */
        http.authorizeHttpRequests(
                request -> request.anyRequest().authenticated()
        );
        /* Define User with all its details
        var user = User.withUsername("john")
                .password("12345").authorities("read").build();

            Declare a UserDetailsService to store the users in memory
            and add the user to be managed by our UserDetailsService

        var userDetailsService = new InMemoryUserDetailsManager(user);

            The UserDetailsService is now set up using the SecurityFilterChain bean

        http.userDetailsService(userDetailsService);
        */
        http.authenticationProvider(customAuthProvider);
        return http.build();
    }

}
