package com.app.todoapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/auth/")
                || request.getMethod().equals("OPTIONS");
    }

   @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    System.out.println("JWT FILTER HIT: " + request.getRequestURI());

    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);
        String email = jwtUtil.extractEmail(token);

        System.out.println("JWT EMAIL: " + email);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email, null, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(auth);
    } else {
        System.out.println("NO AUTH HEADER");
    }

    filterChain.doFilter(request, response);
}

}