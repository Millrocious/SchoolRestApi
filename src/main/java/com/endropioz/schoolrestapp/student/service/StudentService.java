package com.endropioz.schoolrestapp.student.service;

import com.endropioz.schoolrestapp.student.dto.StudentRequestDto;
import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;
import com.endropioz.schoolrestapp.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    StudentResponseDto addNewStudent(StudentRequestDto studentRequestDto);

    Page<StudentResponseDto> getAllStudents(Pageable pageable);

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudentById(Long id, StudentRequestDto studentRequestDto);

    void deleteStudentById(Long id);

    Student getExistingStudentById(Long id);

    void saveStudents(List<StudentRequestDto> studentDtoList);

    void uploadFromFile(MultipartFile file);

    void processCSVFile(MultipartFile file);

    void processExcelFile(MultipartFile file);
}
