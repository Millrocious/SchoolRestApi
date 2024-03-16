package com.endropioz.schoolrestapp.attendance.repository;

import com.endropioz.schoolrestapp.attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Page<Attendance> findAttendancesByLessonId(Long lessonId, Pageable pageable);
}
