package com.endropioz.schoolrestapp.module.dto;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherResponseDto;

public record ModuleSubjectClassTeacherResponseDto(
        SubjectResponseDto subject,
        ClassGroupResponseDto classGroup,
        TeacherResponseDto teacher
) {
}
