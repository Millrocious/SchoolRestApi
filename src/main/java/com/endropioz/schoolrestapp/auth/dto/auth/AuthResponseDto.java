package com.endropioz.schoolrestapp.auth.dto.auth;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}

