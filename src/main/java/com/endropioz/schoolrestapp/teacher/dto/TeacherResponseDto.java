package com.endropioz.schoolrestapp.teacher.dto;

public record TeacherResponseDto(
        Long id,

        String firstName,

        String lastName,

        String email,

        String phone
) {
}


