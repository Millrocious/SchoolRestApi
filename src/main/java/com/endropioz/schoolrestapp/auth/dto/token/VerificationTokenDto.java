package com.endropioz.schoolrestapp.auth.dto.token;

import java.time.LocalDateTime;

public record VerificationTokenDto(
        String token,
        LocalDateTime expiryDate
) {
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}
