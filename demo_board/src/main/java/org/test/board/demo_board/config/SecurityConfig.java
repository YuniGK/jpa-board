package org.test.board.demo_board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* https://velog.io/@shon5544/Spring-Security-1.-%EC%84%A4%EC%A0%95 - 참고 */
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(antMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
