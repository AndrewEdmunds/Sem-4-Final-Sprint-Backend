package com.keyin.finalsprint.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;





@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
        .securityMatchers( (matchers) -> matchers.requestMatchers("/signup", "/login"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).httpBasic();
        return http.build();
    }
    

}



