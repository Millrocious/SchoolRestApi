package com.endropioz.schoolrestapp.grade.dto;

import com.endropioz.schoolrestapp.grade.entity.GradeType;
import com.endropioz.schoolrestapp.lesson.dto.LessonResponseDto;
import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;

public record GradeResponseDto(
        Long id,
        StudentResponseDto student,
        LessonResponseDto lesson,
        Integer gradeValue,
        GradeType gradeType
) {
}
