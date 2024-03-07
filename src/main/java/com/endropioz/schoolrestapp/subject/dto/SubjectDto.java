package com.endropioz.schoolrestapp.subject.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectDto(
        @NotBlank(message = "The field cannot be blank")
        String name
) {
}
