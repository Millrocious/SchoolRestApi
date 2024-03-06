package com.endropioz.schoolrestapp.auth.security;

import com.endropioz.schoolrestapp.auth.entity.Role;
import com.endropioz.schoolrestapp.auth.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {
    JwtProperties jwtProperties;

    public Role extractRole(String token) throws JwtException {
        return Jwts.parser()
                .json(new JacksonDeserializer(Maps.of("role", Role.class).build()))
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", Role.class);
    }

    public String extractEmail(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractType(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("type", String.class);
    }

    public String generateAccessToken(User user) {
        return buildToken(user, jwtProperties.getAccessTokenExpiration(), "accessToken");
    }

    public String generateRefreshToken(User user) {
        return buildToken(user, jwtProperties.getRefreshTokenExpiration(), "refreshToken");
    }

    private String buildToken(User user, Long expiration, String type) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("role", user.getRole());
        claims.put("type", type);
        claims.put("id", user.getId());

        return Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtProperties.getSecretKey())
        );
    }

    public Long extractId(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }
}