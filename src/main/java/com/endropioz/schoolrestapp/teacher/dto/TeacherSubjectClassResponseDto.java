package com.endropioz.schoolrestapp.teacher.dto;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;

public record TeacherSubjectClassResponseDto(
        SubjectResponseDto subject,
        ClassGroupResponseDto classGroup
) {
}