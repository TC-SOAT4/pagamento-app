package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilitar CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                // Configurar CORS (Cross-Origin Resource Sharing)
                .cors(cors -> cors.disable())
                // Configurar o controle de acesso às URLs
                .authorizeHttpRequests(authz -> authz
                        // Especificar as permissões para cada caminho
                        .requestMatchers("/", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/actuator/**")
                        .permitAll()  // Permitir acesso total aos caminhos listados
                        .requestMatchers("/private/**")
                        .hasAnyRole("CLIENTE")  // Caminhos privados exigem um papel específico
                        .anyRequest()
                        .authenticated()  // Todos os outros caminhos exigem autenticação
                )
                // Configurar o login básico
                .httpBasic(httpBasic -> {})
                // Configurar o logout
                .logout(logout -> logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            // Personalizar o que ocorre no sucesso do logout
                        })
                );

        return http.build();
    }
}