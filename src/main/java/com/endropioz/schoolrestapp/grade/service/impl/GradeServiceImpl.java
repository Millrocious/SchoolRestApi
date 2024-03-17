package com.endropioz.schoolrestapp.grade.service.impl;

import com.endropioz.schoolrestapp.grade.dto.GradeRequestDto;
import com.endropioz.schoolrestapp.grade.dto.GradeResponseDto;
import com.endropioz.schoolrestapp.grade.entity.Grade;
import com.endropioz.schoolrestapp.grade.mapper.GradeMapper;
import com.endropioz.schoolrestapp.grade.repository.GradeRepository;
import com.endropioz.schoolrestapp.grade.service.GradeService;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import com.endropioz.schoolrestapp.lesson.repository.LessonRepository;
import com.endropioz.schoolrestapp.student.entity.Student;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    LessonRepository lessonRepository;

    GradeMapper gradeMapper = GradeMapper.MAPPER;

    @Override
    @Transactional
    public GradeResponseDto addGrade(GradeRequestDto gradeDto) {
        Student student = studentRepository.getReferenceById(gradeDto.studentId());
        Lesson lesson = lessonRepository.getReferenceById(gradeDto.lessonId());

        checkIfGradeAlreadyExists(gradeDto);

        Grade grade = gradeMapper.toEntity(gradeDto);

        grade.setStudent(student);
        grade.setLesson(lesson);

        try {
            gradeRepository.save(grade);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("lesson id or student id are not correct!");
        }

        return gradeMapper.toResponseDto(grade);
    }



    @Override
    @Transactional(readOnly = true)
    public GradeResponseDto getGradeById(Long gradeId) {
        Grade grade = getExistingGradeById(gradeId);

        return gradeMapper.toResponseDto(grade);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDto> getGradesByModuleId(Long moduleId, Pageable pageable) {
        return gradeRepository.getGradesByModuleId(moduleId, pageable)
                .map(gradeMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDto> getGradesByStudentId(Long studentId, Pageable pageable) {
        return gradeRepository.getGradesByStudentId(studentId, pageable)
                .map(gradeMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDto> getGradesByLessonId(Long lessonId, Pageable pageable) {
        return gradeRepository.getGradesByLessonId(lessonId, pageable)
                .map(gradeMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDto> getGradesByModuleIdAndStudentId(Long moduleId, Long studentId, Pageable pageable) {
        return gradeRepository.getGradesByModuleIdAndStudentId(moduleId, studentId, pageable)
                .map(gradeMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeResponseDto> getGradesByLessonIdAndStudentId(Long lessonId, Long studentId, Pageable pageable) {
        return gradeRepository.getGradesByLessonIdAndStudentId(lessonId, studentId, pageable)
                .map(gradeMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponseDto> getGradesByStudentIdAndDate(Long studentId, ZonedDateTime date) {
        List<Grade> gradeList = gradeRepository.findByStudentIdAndLessonDate(studentId, date);
        return gradeList.stream()
                .map(gradeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GradeResponseDto updateGrade(Long gradeId, GradeRequestDto gradeDto) {
        Grade grade = getExistingGradeById(gradeId);

        if (!grade.getGradeType().equals(gradeDto.gradeType())) {
            checkIfGradeAlreadyExists(gradeDto);
        }

        gradeMapper.updateEntity(gradeDto, grade);

        return gradeMapper.toResponseDto(gradeRepository.save(grade));
    }

    @Override
    @Transactional
    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    private void checkIfGradeAlreadyExists(GradeRequestDto gradeDto) {
        Optional<Grade> checkingGrade = gradeRepository.
                getGradeByLessonIdAndStudentIdAndGradeType(
                        gradeDto.lessonId(),
                        gradeDto.studentId(),
                        gradeDto.gradeType());

        if (checkingGrade.isPresent()) {
            throw new IllegalArgumentException("grade with such parameters already exists");
        }
    }

    private Grade getExistingGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found"));
    }
}