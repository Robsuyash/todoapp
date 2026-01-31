package com.app.todoapp.config;

import com.app.todoapp.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

// @Bean
// public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
//     org.springframework.web.cors.CorsConfiguration config =
//             new org.springframework.web.cors.CorsConfiguration();

//     config.setAllowCredentials(true);
//     config.setAllowedOrigins(
//             java.util.List.of("http://127.0.0.1:5500")
//     );
//     config.setAllowedMethods(
//             java.util.List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
//     );
//     config.setAllowedHeaders(java.util.List.of("*"));

//     org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
//             new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", config);

//     return source;
// }


}
