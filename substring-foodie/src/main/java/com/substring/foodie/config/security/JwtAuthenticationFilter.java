package com.substring.foodie.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Authorization= Bearer 1235152525625
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer "))
        {
            //sab thik thak hai.
            String token = authorization.substring(7);

            if (jwtService.validateToken(token) && jwtService.isAccessToken(token)) {

                // authentication bata sakte hai.
                String username = jwtService.getUsername(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }


            }


        }

        filterChain.doFilter(request, response);


    }
}