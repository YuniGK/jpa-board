package org.test.board.demo_board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* https://velog.io/@shon5544/Spring-Security-1.-%EC%84%A4%EC%A0%95 - 참고 */
        http
                //csrf 설정
                .csrf((csrf) ->
                    csrf.disable()
                )
                //세션 설정
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //인가 설정
                /*
                * .permitAll() : 아무런 권한이 없어도 전부 요청을 사용
                * .hasRole(String) : 매개변수로 권한이름을 넣어준다. 딱 하나만 적는다. 해당 권한을 가지고 있는 사람들만 요청
                *   ex) httpRequest.requestMatchers("somePath").hasAnyRole("ADMIN", "MANAGER");
                *       httpRequest.requestMatchers("somePath").hasRole("ADMIN");
                * .anyRequest() : requestMatchers()에 적힌 path들을 제외하고 나머지 모든 경로
                * .authenticated() : 일단 인증만 되어있으면 해당 path 요청을 마음대로 할 수 있다.
                * */
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(antMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
