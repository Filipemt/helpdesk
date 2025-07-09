package br.com.filipecode.DeskhelpApi.security.config;

import br.com.filipecode.DeskhelpApi.security.user.CustomUserDetailsService;
import br.com.filipecode.DeskhelpApi.shared.handler.CustomAccessDeniedHandler;
import br.com.filipecode.DeskhelpApi.shared.handler.CustomAuthenticationEntryPointHandler;
import br.com.filipecode.DeskhelpApi.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(this.authenticationEntryPointHandler)
                        .accessDeniedHandler(this.accessDeniedHandler)
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return new CustomUserDetailsService(usuarioRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
