package com.endropioz.schoolrestapp.classgroup.dto;

import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;

import java.util.List;

public record ClassGroupResponseDto (
    Long id,
    String name,

    List<StudentResponseDto> students
) {
}
