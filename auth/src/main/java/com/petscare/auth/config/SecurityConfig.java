package com.petscare.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Permite configurar orígenes desde properties (coma-separados).
    // Ejemplos:
    // cors.allowed-origins=http://localhost:5173,http://localhost:3000
    // En dev puedes dejar * (no recomendado en prod).
    @Value("${cors.allowed-origins:*}")
    private String allowedOriginsProp;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())                   // usa el bean corsConfigurationSource()
            .csrf(csrf -> csrf.disable())                      // API stateless
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Si quisieras abrir solo auth y salud, podrías especificar aquí. Por ahora dejamos todo abierto.
                .requestMatchers("/error", "/actuator/**", "/api/**").permitAll()
                .anyRequest().permitAll()
            );
        return http.build();
    }

    // CORS: permite orígenes definidos en properties
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Procesa la lista de orígenes
        List<String> allowedOrigins = "*".equals(allowedOriginsProp)
                ? List.of("*")
                : Arrays.stream(allowedOriginsProp.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .toList();

        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        // Si más adelante usas cookies/credenciales entre dominios, cambia a true y NO uses "*"
        config.setAllowCredentials(false);
        // (Opcional) Exponer headers personalizados al front:
        // config.setExposedHeaders(List.of("Location"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
