package com.suaninc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**").permitAll() // 인증이 필요하지 않은 경로
                .requestMatchers(request -> isLocalRequest(request)).permitAll() // 로컬 요청 허용
                .anyRequest().authenticated() // 나머지는 인증 필요
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 로컬 요청 감지 함수
    private boolean isLocalRequest(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        return remoteAddr.startsWith("127.") || remoteAddr.startsWith("0:0:0:0:0:0:0:1") 
               || remoteAddr.startsWith("192.168."); // 로컬 네트워크 대역 허용
    }

    // JwtAuthenticationFilter 내부 클래스
    private class JwtAuthenticationFilter extends OncePerRequestFilter {
        private final JwtTokenUtil jwtTokenUtil;

        public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
            this.jwtTokenUtil = jwtTokenUtil;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            // 🚀 로컬 요청이면 JWT 검증을 건너뛰고 다음 필터로 넘김
            if (isLocalRequest(request)) {
                System.out.println("✅ Local request detected, skipping JWT authentication.");
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken("localUser", null, new ArrayList<>())
                );
                filterChain.doFilter(request, response);
                return;
            }

            HttpSession session = request.getSession(false);
            if (session != null) {
                String token = (String) session.getAttribute("jwtToken");
                System.out.println("🔍 Token from session: " + token);

                if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    try {
                        if (jwtTokenUtil.validateToken(token, jwtTokenUtil.extractClientId(token))) {

                            String role = jwtTokenUtil.extractRole(token);
                            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(jwtTokenUtil.extractClientId(token), null, authorities);

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }

                    } catch (io.jsonwebtoken.ExpiredJwtException e) {
                        System.out.println("⚠️ Token expired but ignored: " + e.getMessage());
                        // 인증 없이 통과 (로그인 안 된 상태로 계속 진행)
                    } catch (Exception e) {
                        System.out.println("❗ JWT 처리 중 오류: " + e.getMessage());
                    }
                }
            }

            filterChain.doFilter(request, response);
        }

        // 로컬 요청 감지 함수
        private boolean isLocalRequest(HttpServletRequest request) {
            String remoteAddr = request.getRemoteAddr();
            return remoteAddr.startsWith("127.") 
                || remoteAddr.startsWith("0:0:0:0:0:0:0:1") 
                || remoteAddr.startsWith("192.168."); 
        }

    }
}