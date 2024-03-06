package com.endropioz.schoolrestapp.student.dto;

import lombok.Getter;


public record StudentResponseDto (
        @Getter
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
