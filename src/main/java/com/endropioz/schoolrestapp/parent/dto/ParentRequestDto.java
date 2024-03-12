package com.endropioz.schoolrestapp.parent.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ParentRequestDto(
        @NotBlank(message = "Name is mandatory")
        String firstName,
        @NotBlank(message = "Last name is mandatory")
        String lastName,
        @Email
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone must contain only digits and an optional plus symbol")
        String phone,

        @NotNull(message = "Children is mandatory")
        List<Long> childrenId
) {
}
