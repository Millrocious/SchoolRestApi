package com.endropioz.schoolrestapp.teacher.service;

import com.endropioz.schoolrestapp.teacher.dto.TeacherRequestDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherResponseDto> getAllTeachers(Pageable pageable);

    TeacherResponseDto getTeacherById(Long id);

    TeacherResponseDto addNewTeacher(TeacherRequestDto teacherRequestDto);

    TeacherResponseDto updateTeacherById(Long id, TeacherRequestDto teacherDto);

    void deleteTeacherById(Long id);
}
