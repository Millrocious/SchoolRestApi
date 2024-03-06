package com.endropioz.schoolrestapp.auth.security;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtProperties {
    String secretKey;

    long accessTokenExpiration;
    long refreshTokenExpiration;
}
