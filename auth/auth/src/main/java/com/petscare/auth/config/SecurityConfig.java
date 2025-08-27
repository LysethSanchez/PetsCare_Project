package com.petscare.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// Si prefieres configurar CORS aquí (en vez de WebConfig), descomenta estos imports y el bean de cors() más abajo.
// import java.util.List;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configuración principal de seguridad para API REST:
     * - CSRF deshabilitado (API stateless)
     * - CORS habilitado (usa tu WebConfig o el bean comentado abajo)
     * - Sin sesiones (STATELESS)
     * - Todas las rutas permitidas por ahora (no tienes autenticación con Spring Security)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CORS: habilita la capa de CORS de Spring Security (las reglas las defines en WebConfig o en el bean de cors() abajo)
            .cors(Customizer.withDefaults())
            // CSRF no aplica para APIs stateless
            .csrf(csrf -> csrf.disable())
            // No usar sesiones en el servidor
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Autorización: por ahora todo abierto (tus controladores ya manejan la lógica)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/error",
                    "/actuator/**",
                    "/api/**"             // abre todos tus endpoints de la API
                ).permitAll()
                .anyRequest().permitAll()
            );

        return http.build();
    }

    /*
    // OPCIONAL: Si NO tienes WebConfig o quieres declarar CORS aquí mismo,
    // descomenta este bean y los imports de arriba.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // En producción coloca el dominio del frontend en lugar de "*"
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    */
}
