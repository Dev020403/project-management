package com.project_managment.projectManagmentSystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key;

    static {
        try {
            key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize JWT key", e);
        }
    }

    public static String generateToken(Authentication auth) {
        try {
            return Jwts.builder()
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 8640000))
                    .claim("email", auth.getName())
                    .signWith(key)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    public static String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().
                parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }

}
