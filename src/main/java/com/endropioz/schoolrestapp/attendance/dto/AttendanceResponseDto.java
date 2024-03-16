package com.endropioz.schoolrestapp.attendance.dto;

import com.endropioz.schoolrestapp.attendance.entity.AttendanceType;

public record AttendanceResponseDto(
        Long id,
        Long lessonId,
        Long studentId,
        AttendanceType attendanceType
) {
}
