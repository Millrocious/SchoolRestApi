package com.endropioz.schoolrestapp.auth.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(
        @NotBlank(message = "Enter the email")
        String email,

        @NotBlank(message = "Enter the password")
        String password
) {
}

