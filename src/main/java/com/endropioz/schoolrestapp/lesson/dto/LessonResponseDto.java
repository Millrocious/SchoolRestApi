package com.endropioz.schoolrestapp.lesson.dto;

import com.endropioz.schoolrestapp.module.dto.ModuleResponseDto;

import java.time.ZonedDateTime;

public record LessonResponseDto (
    Long id,

    String name,

    ZonedDateTime date,

    Integer period,

    String homework,

    ModuleResponseDto module
){
}