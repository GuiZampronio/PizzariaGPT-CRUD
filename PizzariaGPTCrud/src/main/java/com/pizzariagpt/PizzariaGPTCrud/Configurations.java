package com.pizzariagpt.PizzariaGPTCrud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class Configurations implements WebMvcConfigurer {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.csrf(csrf -> csrf.disable())
                          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                          .authorizeHttpRequests(auth -> auth
                                    .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                    .anyRequest().authenticated()
                          )
                          .build();
        }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();
        }

}
