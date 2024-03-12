package com.endropioz.schoolrestapp.teacher.service.impl;

import com.endropioz.schoolrestapp.teacher.dto.TeacherRequestDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherResponseDto;
import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import com.endropioz.schoolrestapp.teacher.mapper.TeacherMapper;
import com.endropioz.schoolrestapp.teacher.repository.TeacherRepository;
import com.endropioz.schoolrestapp.teacher.service.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;

    TeacherMapper teacherMapper = TeacherMapper.MAPPER;

    @Override
    public Page<TeacherResponseDto> getAllTeachers(Pageable pageable) {
        Page<Teacher> teachers = teacherRepository.findAll(pageable);

        return teachers.map(teacherMapper::toResponseDto);
    }

    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = getExistingTeacherById(id);

        return teacherMapper.toResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto addNewTeacher(TeacherRequestDto teacherDto) {
        return teacherMapper.toResponseDto(teacherRepository.save(teacherMapper.toEntity(teacherDto)));
    }

    @Override
    public TeacherResponseDto updateTeacherById(Long id, TeacherRequestDto teacherDto) {
        Teacher teacher = getExistingTeacherById(id);

        teacherMapper.updateEntity(teacherDto, teacher);
        teacherRepository.save(teacher);

        return teacherMapper.toResponseDto(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.findById(id).ifPresent(teacherRepository::delete);
    }

    private Teacher getExistingTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }
}
