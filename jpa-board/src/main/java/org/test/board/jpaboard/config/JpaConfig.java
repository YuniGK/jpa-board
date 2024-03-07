package org.test.board.jpaboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("yuni");//TODO: 스프링 시큐리티 인증 기능 추가시 변경 예정
    }
}
