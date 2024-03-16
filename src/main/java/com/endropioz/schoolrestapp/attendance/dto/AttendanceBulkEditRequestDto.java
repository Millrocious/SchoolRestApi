package com.endropioz.schoolrestapp.attendance.dto;

import com.endropioz.schoolrestapp.attendance.entity.AttendanceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AttendanceBulkEditRequestDto(
        @NotEmpty(message = "Attendance IDs can't be empty")
        List<Long> attendanceIds,

        @NotNull(message = "Attendance type can't be null")
        AttendanceType attendanceType
) {
}

