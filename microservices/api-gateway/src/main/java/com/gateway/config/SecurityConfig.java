package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity//why
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {


        //configurations: same configurations is  here:

        httpSecurity.
                cors(e -> e.disable())
                .csrf(e -> e.disable())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET).permitAll()
                                .pathMatchers("/foods/**").hasRole("ADMIN")
                                .pathMatchers(HttpMethod.POST,"/restaurants/**").hasRole("ADMIN")
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(config ->
                        config.jwt(Customizer.withDefaults()))
        ;


        return httpSecurity.build();
    }
}
