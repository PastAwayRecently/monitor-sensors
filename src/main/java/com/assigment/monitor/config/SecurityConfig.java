package com.assigment.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Shmarlouski
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error", "/styles.css", "/favicon.ico").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        // Используем hasAuthority()
                        .requestMatchers(HttpMethod.GET, "/sensors").hasAnyAuthority("ADMINISTRATOR", "VIEWER")
                        .requestMatchers(HttpMethod.GET, "/sensors/**").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/sensors").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/sensors/**").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/sensors/**").hasAuthority("ADMINISTRATOR")

                        // Используем hasAuthority()
                        .requestMatchers(HttpMethod.GET, "/view/sensors").hasAnyAuthority("ADMINISTRATOR", "VIEWER")
                        .requestMatchers(HttpMethod.GET, "/view/sensors/**").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/view/sensors").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/view/sensors/**").hasAuthority("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/view/sensors/**").hasAuthority("ADMINISTRATOR")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/view/sensors", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}