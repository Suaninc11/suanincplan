package com.suaninc;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

            HttpSession session = request.getSession(false); // 세션 가져오기 (존재하지 않으면 null 반환)

            if (session != null) {
                String token = (String) session.getAttribute("jwtToken"); // 세션에서 토큰 가져오기

                if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    try {
                        // 토큰 검증
                        if (jwtTokenUtil.validateToken(token, jwtTokenUtil.extractClientId(token))) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(jwtTokenUtil.extractClientId(token), null, new ArrayList<>());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    } catch (io.jsonwebtoken.ExpiredJwtException e) {
                        // 토큰 만료 시 JavaScript 메시지와 함께 리다이렉트
                        System.out.println("Token expired: " + e.getMessage());
                        session.invalidate(); // 세션 무효화
                        sendRedirectWithMessage(response, "세션이 만료되었습니다. 다시 로그인해 주세요.", "https://visionm.kr/login/login.php");
                        return;
                    } catch (Exception e) {
                        System.out.println("Invalid token in session.");
                        sendRedirectWithMessage(response, "잘못된 인증 정보입니다. 다시 로그인해 주세요.", "https://visionm.kr/login/login.php");
                        return;
                    }
                }
            }

            filterChain.doFilter(request, response);
        }

        // 리다이렉트 전 메시지 출력
        private void sendRedirectWithMessage(HttpServletResponse response, String message, String redirectUrl) throws IOException {
            String script = "<html><head><script>" +
                    "alert('" + message + "');" +
                    "window.location.href = '" + redirectUrl + "';" +
                    "</script></head><body></body></html>";
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(script);
        }
    }
}