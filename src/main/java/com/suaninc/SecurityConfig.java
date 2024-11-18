package com.suaninc;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private static final String CLIENT_ID = "SPECIFIC_CLIENT_ID";  // 특정 클라이언트의 ID

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(request -> isLocalRequest(request)).permitAll() // 로컬 요청 허용
                .anyRequest().permitAll()
//                .anyRequest().authenticated() // 그 외는 인증 필요
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 로컬 요청 감지 함수
    private boolean isLocalRequest(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        return "127.0.0.1".equals(remoteAddr) || "0:0:0:0:0:0:0:1".equals(remoteAddr) || "192:168:56:95".equals(remoteAddr); // IPv6 포함  
    }

    // JWT 필터 클래스 정의
    private class JwtAuthenticationFilter extends OncePerRequestFilter {
        private final JwtTokenUtil jwtTokenUtil;

        public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
            this.jwtTokenUtil = jwtTokenUtil;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            System.out.println("JwtAuthenticationFilter: Processing request " + request.getRequestURI());

            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String clientId = jwtTokenUtil.extractClientId(token);
                if (jwtTokenUtil.validateToken(token, clientId)) {
                    System.out.println("JwtAuthenticationFilter: Token validated for " + clientId);
                    SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(clientId, null, new ArrayList<>()));
                } else {
                    System.out.println("JwtAuthenticationFilter: Invalid token");
                }
            } else {
                System.out.println("JwtAuthenticationFilter: No token found");
            }

            chain.doFilter(request, response);
        }
    }
}
