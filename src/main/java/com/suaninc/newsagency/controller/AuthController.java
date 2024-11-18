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

        if ("WEB_CLIENT_ID".equals(clientId) || "APP_CLIENT_ID".equals(clientId)) {
            String token = jwtTokenUtil.generateToken(clientId);
            return ResponseEntity.ok(token);
        } else {
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
