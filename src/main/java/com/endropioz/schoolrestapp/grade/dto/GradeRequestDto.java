package com.endropioz.schoolrestapp.grade.dto;

import com.endropioz.schoolrestapp.grade.entity.GradeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GradeRequestDto(

    @NotNull(message = "studentId is mandatory!")
    Long studentId,

    @NotNull(message = "lessonId is mandatory!")
    Long lessonId,

    @NotNull(message = "gradeValue is mandatory!")
    @Min(value = 1, message = "gradeValue can't be less than 1")
    @Max(value = 12, message = "gradeValue can't be greater than 12")
    Integer gradeValue,

    @NotNull(message = "gradeType is mandatory!")
    GradeType gradeType
){
}
