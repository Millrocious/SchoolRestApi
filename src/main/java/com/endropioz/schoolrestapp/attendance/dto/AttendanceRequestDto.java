package com.endropioz.schoolrestapp.attendance.dto;

import com.endropioz.schoolrestapp.attendance.entity.AttendanceType;
import jakarta.validation.constraints.NotNull;

public record AttendanceRequestDto (
        @NotNull(message = "Lesson id can't be null")
        Long lessonId,

        @NotNull(message = "Student id can't be null")
        Long studentId,

        @NotNull(message = "Attendance type can't be null")
        AttendanceType attendanceType
) {
}
