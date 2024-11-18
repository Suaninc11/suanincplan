package com.suaninc;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // JWT 서명 키
    private final long validityInMillis = 3600000; // 1시간 (밀리초 단위)

    // JWT 생성 메서드
    public String generateToken(String clientId) {
        return Jwts.builder()
                .setSubject(clientId)  // clientId를 JWT에 포함
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMillis))
                .signWith(key)
                .compact();
    }

    // JWT 검증 메서드
    public boolean validateToken(String token, String clientId) {
        try {
            String subject = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody().getSubject();
            return subject.equals(clientId);  // 토큰의 clientId와 비교
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT에서 clientId 추출
    public String extractClientId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
