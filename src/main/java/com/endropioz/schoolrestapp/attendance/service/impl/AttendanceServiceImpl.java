package com.endropioz.schoolrestapp.attendance.service.impl;

import com.endropioz.schoolrestapp.attendance.dto.AttendanceBulkEditRequestDto;
import com.endropioz.schoolrestapp.attendance.dto.AttendanceRequestDto;
import com.endropioz.schoolrestapp.attendance.dto.AttendanceResponseDto;
import com.endropioz.schoolrestapp.attendance.entity.Attendance;
import com.endropioz.schoolrestapp.attendance.entity.AttendanceType;
import com.endropioz.schoolrestapp.attendance.event.AttendanceEvent;
import com.endropioz.schoolrestapp.attendance.mapper.AttendanceMapper;
import com.endropioz.schoolrestapp.attendance.repository.AttendanceRepository;
import com.endropioz.schoolrestapp.attendance.service.AttendanceService;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import com.endropioz.schoolrestapp.lesson.repository.LessonRepository;
import com.endropioz.schoolrestapp.student.entity.Student;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttendanceServiceImpl implements AttendanceService {
    AttendanceRepository attendanceRepository;
    StudentRepository studentRepository;
    LessonRepository lessonRepository;
    ApplicationEventPublisher eventPublisher;

    AttendanceMapper attendanceMapper = AttendanceMapper.MAPPER;

    @Override
    public AttendanceResponseDto addNewAttendance(AttendanceRequestDto attendanceRequestDto) {
        Attendance entity = attendanceMapper.toEntity(attendanceRequestDto);

        Lesson lesson = lessonRepository.findById(attendanceRequestDto.lessonId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));

        Student student = studentRepository.findById(attendanceRequestDto.studentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        entity.setLesson(lesson);
        entity.setStudent(student);

        Attendance savedAttendance = attendanceRepository.save(entity);

        if (savedAttendance.getAttendanceType() == AttendanceType.ABSENT) {
            publishAttendanceEvent(savedAttendance);
        }

        return attendanceMapper.toResponseDto(savedAttendance);
    }

    @Override
    public Page<AttendanceResponseDto> getAllAttendances(Pageable pageable) {
        Page<Attendance> attendances = attendanceRepository.findAll(pageable);

        return attendances.map(attendanceMapper::toResponseDto);
    }


    @Override
    public AttendanceResponseDto getAttendanceById(Long id) {
        Attendance existingStudent = getExistingAttendanceById(id);

        return attendanceMapper.toResponseDto(existingStudent);
    }

    @Override
    public AttendanceResponseDto updateAttendanceById(Long id, AttendanceRequestDto attendanceRequestDto) {
        Attendance entity = getExistingAttendanceById(id);

        AttendanceType originalType = entity.getAttendanceType();
        attendanceMapper.updateEntity(attendanceRequestDto, entity);
        Attendance updatedAttendance = attendanceRepository.save(entity);

        if (originalType != AttendanceType.ABSENT && updatedAttendance.getAttendanceType() == AttendanceType.ABSENT) {
            publishAttendanceEvent(updatedAttendance);
        }

        return attendanceMapper.toResponseDto(updatedAttendance);
    }

    @Override
    public void deleteAttendanceById(Long id) {
        attendanceRepository.findById(id).ifPresent(attendanceRepository::delete);
    }

    @Override
    public Page<AttendanceResponseDto> getAttendancesByLessonId(Long lessonId, Pageable pageable) {
        Page<Attendance> attendances = attendanceRepository.findAttendancesByLessonId(lessonId, pageable);

        return attendances.map(attendanceMapper::toResponseDto);
    }

    @Override
    public void createAttendancesForLesson(Long lessonId, Long classGroupId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));

        List<Student> students = studentRepository.findAllByClassGroupId(classGroupId);

        for (Student student : students) {
            Attendance attendance = new Attendance();
            attendance.setLesson(lesson);
            attendance.setStudent(student);
            attendance.setAttendanceType(AttendanceType.UNDEFINED);
            attendanceRepository.save(attendance);
        }
    }

    @Override
    public void bulkEdit(AttendanceBulkEditRequestDto attendanceBulkEditRequestDto) {
        List<Attendance> attendances = attendanceRepository.findAllById(attendanceBulkEditRequestDto.attendanceIds());

        for (Attendance attendance : attendances) {
            attendance.setAttendanceType(attendanceBulkEditRequestDto.attendanceType());
            attendanceRepository.save(attendance);

            if (attendanceBulkEditRequestDto.attendanceType() == AttendanceType.ABSENT) {
                publishAttendanceEvent(attendance);
            }
        }
    }

    @Override
    public void publishAttendanceEvent(Attendance attendance) {
        AttendanceEvent attendanceEvent = new AttendanceEvent(this, attendance);
        eventPublisher.publishEvent(attendanceEvent);
    }

    private Attendance getExistingAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance not found"));
    }
}
