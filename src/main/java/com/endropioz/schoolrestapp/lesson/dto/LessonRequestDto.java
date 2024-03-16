package com.endropioz.schoolrestapp.lesson.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record LessonRequestDto (

    @NotBlank
    String name,

    @NotNull
    ZonedDateTime date,

    @Min(0) @Max(8)
    @NotNull
    Integer period,

    String homework,

    @NotNull
    Long moduleId
) {
}