package com.vmware.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    /*@Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .formLogin()
                ;

        return http.build();
    }*/
}
