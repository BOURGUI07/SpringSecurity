package com.example.configure_Endpoint_level_Authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN") // or .authorities("ROLE_ADMIN")
                .build();
        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER") // or .authorities("ROLE_MANAGER")
                .build();
        manager.createUser(user1);
        manager.createUser(user2);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        /*
            http.authorizeHttpRequests(r->r
                .requestMatchers("/hello").hasRole("ADMIN")
                .requestMatchers("/ciao").hasRole("MANAGER")
                .anyRequest().authenticated());
         */

        /*
            http.authorizeHttpRequests(t->t
                .requestMatchers(HttpMethod.GET,"/a").authenticated()
                .requestMatchers(HttpMethod.POST,"/a").permitAll()
                .anyRequest().denyAll());
         */

        /*
            http.authorizeHttpRequests(t->t
                .requestMatchers(HttpMethod.GET,"/a/b/**").authenticated()
                .anyRequest().permitAll());
         */
        http.authorizeHttpRequests(t->t
                .requestMatchers("/products/{code:^[0-9]*$}").permitAll()
                .anyRequest().denyAll());


        // anyRequest() can't come before requestMatchers()
        // always the order of rules go from particular to general
        http.csrf(AbstractHttpConfigurer::disable);// to be able to call ALL endpoints
        return http.build();
    }
}
