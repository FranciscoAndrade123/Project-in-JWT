package com.example.veterinarinary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.veterinarinary.jwt.JwtAuthenticationFilter;
import com.example.veterinarinary.repository.IUser;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter JwtAuthenticationFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Endpoints públicos
                .requestMatchers(
                    "/api/v1/public/users/register",
                    "/api/v1/public/users/login",
                    "/api/recovery-requests/create",
                    "/password-email/sendTestEmail/**",
                    "/password-email/forgotPassword/**",
                    "/password/reset"
                ).permitAll()

                // Endpoints de gestión de usuarios (solo ADMIN)
                .requestMatchers(
                    "/api/users",               // GET todos los usuarios
                    "/api/users/{id}",          // GET usuario por id, PUT, DELETE
                    "/api/users/{id}/role"      // Cambiar rol de usuario
                ).hasRole("ADMIN")
                
                // Perfil de usuario autenticado (CLIENTE y VETERINARIO)
                .requestMatchers(
                    "/api/users/profile"
                ).hasAnyRole("CLIENTE", "VETERINARIO", "ADMIN")
                
                // Permitir que cada usuario edite su perfil (ver lógica en el controller con @PreAuthorize)
                .requestMatchers(
                    "/api/users/{id}"
                ).authenticated()

                // Recuperación de contraseña (CLIENTE y ADMIN)
                .requestMatchers(
                    "/api/recovery-requests/user/**",
                    "/api/recovery-requests/{id}",
                    "/api/recovery-requests/by-token/**",
                    "/api/recovery-requests/use/**"
                ).hasAnyRole("CLIENTE", "ADMIN")

                // Gestión de roles, páginas, page-roles, permission-roles (solo ADMIN)
                .requestMatchers(
                    "/api/roles/**",
                    "/api/pages/**",
                    "/api/page-roles/**",
                    "/api/permission-roles/**"
                ).hasRole("ADMIN")

                // Gestión de mascotas (CLIENTE y VETERINARIO)
                .requestMatchers(
                    "/api/v1/pet/**"
                ).hasAnyRole("CLIENTE", "VETERINARIO", "ADMIN")

                // Gestión de razas y lugares (todos pueden consultar, solo ADMIN puede modificar)
                .requestMatchers(
                    "/api/v1/breed/",
                    "/api/v1/breed/{id}",
                    "/api/v1/breed/filter/**",
                    "/api/v1/place/",
                    "/api/v1/place/{id}",
                    "/api/v1/place/filter/**"
                ).permitAll()
                .requestMatchers(
                    "/api/v1/breed/**",
                    "/api/v1/place/**"
                ).hasRole("ADMIN")

                // Gestión de especialidades y tratamientos (VETERINARIO y ADMIN)
                .requestMatchers(
                    "/api/v1/specialty/**",
                    "/api/v1/treatment/**"
                ).hasAnyRole("VETERINARIO", "ADMIN")

                // Gestión de citas (appointments)
                .requestMatchers(
                    "/api/v1/appointment/", // POST (crear)
                    "/api/v1/appointment/{id}", // GET, PUT, DELETE
                    "/api/v1/appointment/filter/**",
                    "/api/v1/appointment/filterPetName/**"
                ).hasAnyRole("CLIENTE", "VETERINARIO", "ADMIN")

                // Relación citas-tratamientos (solo VETERINARIO y ADMIN)
                .requestMatchers(
                    "/api/v1/appointmentTreatment/**"
                ).hasAnyRole("VETERINARIO", "ADMIN")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            );
        http.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                        PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(IUser userRepository) {
        return username -> userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}