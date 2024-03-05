package com.endropioz.schoolrestapp.auth.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "auth.message")
public class AuthMessages {
    String confirmed;
    String invalidToken;
    String expired;
    String tokenNotFound;
}
