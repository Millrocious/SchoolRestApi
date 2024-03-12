package com.endropioz.schoolrestapp.teacher.service;

import com.endropioz.schoolrestapp.teacher.dto.TeacherSubjectClassResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherSubjectClassService {
    Page<TeacherSubjectClassResponseDto> getAllTeacherSubjectClasses(Pageable pageable);

    Page<TeacherSubjectClassResponseDto> getAllTeacherSubjectClassesByTeacherId(Long teacherId, Pageable pageable);

    @Transactional
    void addSubjectClassToTeacher(Long teacherId, Long subjectId, Long classGroupId);

    void deleteTeacherSubjectClass(Long teacherId, Long subjectId, Long classGroupId);
}
