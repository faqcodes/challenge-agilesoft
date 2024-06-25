package com.faqcodes.agilesoft.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.faqcodes.agilesoft.shared.security.JwtAuthenticationFilter;
import com.faqcodes.agilesoft.shared.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    final var jwtTokenProvider = new JwtTokenProvider();
    final var jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);

    return http
        .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (consider enabling for production)
        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
            .requestMatchers(
                "/api/users/signin",
                "/api/users/signup")
            .permitAll()
            .anyRequest()
            .authenticated()) // Require authentication for all other requests
        .exceptionHandling(exceptionHandling -> exceptionHandling
            .accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(authenticationEntryPoint()))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
    accessDeniedHandler.setErrorPage("/api/error/403"); // Customize the error page or handle directly
    return accessDeniedHandler;
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new Http403ForbiddenEntryPoint();
  }
}
