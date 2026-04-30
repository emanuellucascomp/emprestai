package br.com.emanuel.emprestai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable()) // Permite H2 Console
            )
            .authorizeHttpRequests(authorize -> authorize
                // Endpoints públicos - sem autenticação
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/reset-password").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/customers").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/stores").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/stores").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/administrators/register").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                // Todos os outros endpoints requerem autenticação
                .anyRequest().permitAll() // Temporariamente permitAll() para testes
            )
            .httpBasic(basic -> {}); // Habilita autenticação básica (debug)

        return http.build();
    }
}

