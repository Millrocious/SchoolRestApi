package com.endropioz.schoolrestapp.auth.dto.token;

public record RefreshTokenResponseDto(
        String accessToken,

        String refreshToken
) {
}

