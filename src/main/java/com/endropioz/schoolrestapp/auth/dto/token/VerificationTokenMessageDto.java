package com.endropioz.schoolrestapp.auth.dto.token;

import org.springframework.http.HttpStatus;

public record VerificationTokenMessageDto(
        boolean success,
        String message,
        HttpStatus status
) {
}

