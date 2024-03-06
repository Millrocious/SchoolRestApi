package com.endropioz.schoolrestapp.student.service.impl;

import com.endropioz.schoolrestapp.student.dto.StudentRequestDto;
import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;
import com.endropioz.schoolrestapp.student.entity.Student;
import com.endropioz.schoolrestapp.student.mapper.StudentMapper;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
import com.endropioz.schoolrestapp.student.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    StudentMapper studentMapper = StudentMapper.MAPPER;

    @Override
    public StudentResponseDto addNewStudent(StudentRequestDto studentRequestDto) {
        Student newStudent = studentMapper.toEntity(studentRequestDto);

        return studentMapper.toResponseDto(studentRepository.save(newStudent));
    }

    @Override
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);

        return students.map(studentMapper::toResponseDto);
    }


    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student existingStudent = getExistingStudentById(id);

        return studentMapper.toResponseDto(existingStudent);
    }

    @Override
    public StudentResponseDto updateStudentById(Long id, StudentRequestDto studentRequestDto) {
        Student existingStudent = getExistingStudentById(id);

        studentMapper.updateEntity(studentRequestDto, existingStudent);
        studentRepository.save(existingStudent);

        return studentMapper.toResponseDto(existingStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.findById(id).ifPresent(student -> {
            long millis = System.currentTimeMillis();
            String email = student.getEmail();
            String phone = student.getPhone();

            email = String.format("%s_%s", email, millis);
            phone = String.format("%s_%s", phone, millis);

            student.setEmail(email);
            student.setPhone(phone);
            student.setDeleted(true);

            studentRepository.save(student);
        });
    }

    @Override
    public Student getExistingStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @Override
    public void saveStudents(List<StudentRequestDto> studentDtoList) {
        List<Student> students = studentDtoList.stream()
                .map(studentMapper::toEntity)
                .toList();
        studentRepository.saveAll(students);
    }
}
