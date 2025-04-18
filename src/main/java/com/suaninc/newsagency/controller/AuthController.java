package com.suaninc.newsagency.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suaninc.JwtTokenUtil;

import jakarta.servlet.http.HttpSession;

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
            String token = jwtTokenUtil.generateToken(clientId, "AD");
            System.out.println("Generated JWT Token: " + token); // 토큰 로그 출력
            return ResponseEntity.ok(token);
        } else {
            System.out.println("Unauthorized client: " + clientId); // 로그 추가
            return ResponseEntity.status(401).body("Unauthorized client");
        }
    }
    
    @GetMapping("/session/status")
    @ResponseBody
    public Map<String, Object> checkSessionStatus(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String token = (String) session.getAttribute("jwtToken");

        if (token != null && jwtTokenUtil.validateToken(token, jwtTokenUtil.extractClientId(token))) {
            result.put("valid", true);
        } else {
            result.put("valid", false);
        }
        result.put("valid", true);
        return result;
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
