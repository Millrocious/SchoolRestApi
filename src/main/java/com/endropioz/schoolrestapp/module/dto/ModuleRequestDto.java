package com.endropioz.schoolrestapp.module.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

public record ModuleRequestDto (
    @NotBlank
    String name,

    @NotNull
    ZonedDateTime startDate,

    @NotNull
    ZonedDateTime endDate,

    @NotEmpty
    Map<DayOfWeek, Set<Integer>> schedule
) {
}
