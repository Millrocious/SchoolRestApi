package com.endropioz.schoolrestapp.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TeacherRequestDto(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone must contain only digits and an optional plus symbol")
        String phone
) {
}

//
//Long id;
//
//
//
//List<SubjectDto> subjects;
//List<TeacherResponseClassGroupDto> classes;