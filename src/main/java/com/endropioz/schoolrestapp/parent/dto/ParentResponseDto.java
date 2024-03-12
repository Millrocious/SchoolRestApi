package com.endropioz.schoolrestapp.parent.dto;

import com.endropioz.schoolrestapp.student.entity.Student;

import java.util.List;

public record ParentResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<Student> children
) {
}
