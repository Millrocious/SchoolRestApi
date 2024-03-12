package com.endropioz.schoolrestapp.subject.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectRequestDto(
        @NotBlank(message = "The field cannot be blank")
        String name
) {
}
