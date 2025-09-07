package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // para API, luego puedes reforzar
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(Customizer.withDefaults());

        // Cabeceras de seguridad básicas
        http.headers(headers -> headers
                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).preload(true))
                .xssProtection(Customizer.withDefaults())
                .frameOptions(frame -> frame.sameOrigin())
                .referrerPolicy(ref -> ref.policy(org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))
                .contentTypeOptions(Customizer.withDefaults())
        );

        return http.build();
    }
}
