package com.endropioz.schoolrestapp.attendance.service;

import com.endropioz.schoolrestapp.attendance.dto.AttendanceBulkEditRequestDto;
import com.endropioz.schoolrestapp.attendance.dto.AttendanceRequestDto;
import com.endropioz.schoolrestapp.attendance.dto.AttendanceResponseDto;
import com.endropioz.schoolrestapp.attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttendanceService {
    AttendanceResponseDto addNewAttendance(AttendanceRequestDto attendanceRequestDto);

    Page<AttendanceResponseDto> getAllAttendances(Pageable pageable);

    AttendanceResponseDto getAttendanceById(Long id);

    AttendanceResponseDto updateAttendanceById(Long id, AttendanceRequestDto attendanceRequestDto);

    void deleteAttendanceById(Long id);

    Page<AttendanceResponseDto> getAttendancesByLessonId(Long lessonId, Pageable pageable);

    void createAttendancesForLesson(Long lessonId, Long classGroupId);

    void bulkEdit(AttendanceBulkEditRequestDto attendanceBulkEditRequestDto);

    void publishAttendanceEvent(Attendance attendance);
}
