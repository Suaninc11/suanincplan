package com.suaninc;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private static final String SECRET_KEY_STRING = "my-fixed-secret-key-which-is-very-secure";
	private final SecretKey key;

	public JwtTokenUtil() {
	    byte[] decodedKey = Base64.getEncoder().encode(SECRET_KEY_STRING.getBytes());
	    this.key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
	}
	
    private final long validityInMillis = 7200000; // 2시간 (밀리초 단위)

    // JWT 생성 메서드
    public String generateToken(String clientId, String role) {
        return Jwts.builder()
                .setSubject(clientId)  // clientId를 JWT에 포함
                .claim("role", role)
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
            System.out.println("Received ClientId: " + subject);
            System.out.println("Expected ClientId: " + clientId);
            return subject.equals(clientId);
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    // JWT에서 clientId 추출
    public String extractClientId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody()
                .get("role", String.class);
    }
}
