package com.endropioz.schoolrestapp.auth.dto.token;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(
        @NotBlank
        String refreshToken
) {
}

