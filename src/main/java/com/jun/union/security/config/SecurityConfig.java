package com.jun.union.security.config;

import com.jun.union.security.handler.CustomAccessDeniedHandler;
import com.jun.union.security.handler.CustomAuthenticationEntryPoint;
import com.jun.union.security.jwt.JwtAuthenticationFilter;
import com.jun.union.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    @Bean
    public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .httpBasic().disable() // rest api이므로 기본설정 미사용
            .csrf().disable() // rest api이므로 csrf 보안 미사용
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt로 인증하므로 세션 미사용
            .and()
            .authorizeRequests()
            .antMatchers("/api/user/register/**").permitAll()
            .antMatchers("/api/user/login/**").permitAll()
            .antMatchers("/api/user/change/**").permitAll()
            .antMatchers("/api/user/issue/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            .and()
            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class).build(); // jwt 필터 추가
    }

}

