package com.substring.foodie.config.security;

import com.substring.foodie.config.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(e -> e.disable())
                .authorizeHttpRequests(request ->
                                request.requestMatchers("/api/v1/**").permitAll()
                                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-resources/**").permitAll()
                                        .anyRequest().authenticated()
//                        request.requestMatchers("/api/v1/auth/login","/api/v1/auth/refresh-token").permitAll().
//                                requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll().
//                                requestMatchers(HttpMethod.DELETE, "/api/v1/users/**", "api/v1/restaurants/**").hasRole(AppConstants.ROLE_ADMIN)
//                                .requestMatchers(HttpMethod.POST, "/api/v1/restaurants/**").hasRole(AppConstants.ROLE_ADMIN)
//                                .requestMatchers(HttpMethod.PUT, "/api/v1/restaurants/**").hasRole(AppConstants.ROLE_ADMIN)
//                                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                                .anyRequest().authenticated()
                );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(
                exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.FORBIDDEN.value());
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"error\":\"Access denied\"}");

                                })
        );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
