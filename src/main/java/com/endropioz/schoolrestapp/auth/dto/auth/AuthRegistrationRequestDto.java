package com.endropioz.schoolrestapp.auth.dto.auth;

import com.endropioz.schoolrestapp.auth.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthRegistrationRequestDto(
        @NotNull(message = "Role is mandatory")
        Role userType,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}",
                message = "Password must contain at least 8 characters, one uppercase, one lowercase, and one number")
        String password
) {
}
