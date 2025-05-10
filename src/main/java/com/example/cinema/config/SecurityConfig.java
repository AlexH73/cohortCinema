package com.example.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // доступ к H2
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN") // только админ
                        .requestMatchers(new AntPathRequestMatcher("/staff/**")).hasAnyRole("STAFF", "ADMIN") // сотрудники и админ
                        .requestMatchers(new AntPathRequestMatcher("/client/**")).hasAnyRole("CLIENT", "STAFF", "ADMIN") // клиенты, сотрудники, админ
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")) // CSRF для H2 отключен
                        .disable()
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // позволяет отображать H2 Console
                )
                .formLogin(Customizer.withDefaults()); // базовая форма логина

        return http.build();
    }
}