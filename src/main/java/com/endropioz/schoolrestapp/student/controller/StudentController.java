package com.endropioz.schoolrestapp.student.controller;

import com.endropioz.schoolrestapp.student.dto.StudentRequestDto;
import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;
import com.endropioz.schoolrestapp.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    StudentService studentService;

    @GetMapping
    public Page<StudentResponseDto> getAllStudents(@PageableDefault Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }


    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto addNewStudent(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.addNewStudent(studentRequestDto);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudentById(@PathVariable("id") Long id, @RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.updateStudentById(id, studentRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

}
