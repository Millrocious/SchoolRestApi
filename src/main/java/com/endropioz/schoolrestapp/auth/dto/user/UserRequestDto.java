package com.endropioz.schoolrestapp.auth.dto.user;

import com.endropioz.schoolrestapp.auth.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequestDto(
        @Email(message = "Email should be valid")
        String email,

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}",
                message = "Password must contain at least 8 characters, one uppercase, one lowercase, and one number")
        String password,

        @NotNull(message = "Role cannot be null")
        Role role
) {
}

