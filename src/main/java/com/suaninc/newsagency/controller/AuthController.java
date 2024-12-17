package com.suaninc.newsagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suaninc.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // JSON 기반 로그인 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> generateToken(@RequestBody LoginRequest loginRequest) {
        String clientId = loginRequest.getClientId();
        System.out.println("Received clientId: " + clientId); // 로그 추가

        if ("WEB_CLIENT_ID".equals(clientId) || "APP_CLIENT_ID".equals(clientId)) {
            String token = jwtTokenUtil.generateToken(clientId);
            System.out.println("Generated JWT Token: " + token); // 토큰 로그 출력
            return ResponseEntity.ok(token);
        } else {
            System.out.println("Unauthorized client: " + clientId); // 로그 추가
            return ResponseEntity.status(401).body("Unauthorized client");
        }
    }

    // DTO 클래스
    public static class LoginRequest {
        private String clientId;

        public String getClientId() {
            return clientId;
        }
        public void setClientId(String clientId) {
            this.clientId = clientId;
        }
    }
}
