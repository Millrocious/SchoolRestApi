package com.endropioz.schoolrestapp.auth.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthEmailDto(
        @NotBlank(message = "Email should not be blank")
        @Email(message = "Email should be valid")
        String email
) {
}
