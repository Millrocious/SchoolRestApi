package com.endropioz.schoolrestapp.module.dto;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

public record ModuleResponseDto(
        Long id,

        String name,

        ZonedDateTime startDate,

        ZonedDateTime endDate,

        Map<DayOfWeek, Set<Integer>> schedule
) {
}
