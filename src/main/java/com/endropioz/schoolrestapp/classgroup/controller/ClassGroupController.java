package com.endropioz.schoolrestapp.classgroup.controller;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupRequestDto;
import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import com.endropioz.schoolrestapp.classgroup.service.ClassGroupService;
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
@RequestMapping("/api/classes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassGroupController {
    ClassGroupService classGroupService;

    @PostMapping
    public ClassGroupResponseDto createClassGroup(@Valid @RequestBody ClassGroupRequestDto classGroupDto) {
        return classGroupService.createClassGroup(classGroupDto);
    }

    @GetMapping("/{id}")
    public ClassGroupResponseDto getById(@PathVariable Long id) {
        return classGroupService.getClassGroupById(id);
    }

    @GetMapping
    public Page<ClassGroupResponseDto> getAllClassGroups(@PageableDefault Pageable pageable) {
        return classGroupService.getAllClassGroups(pageable);
    }

    @PutMapping("/{id}")
    public ClassGroupResponseDto updateClassGroup(
            @PathVariable Long id,
            @Valid @RequestBody ClassGroupRequestDto classGroupDto
    ) {
        return classGroupService.updateClassGroup(id, classGroupDto);
    }

//    @PutMapping("/{classGroupId}/student/{studentId}")
//    public void addStudentToClassGroup(@PathVariable Long classGroupId, @PathVariable Long studentId) {
//        classGroupService.addStudentToClassGroup(classGroupId, studentId);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassGroup(@PathVariable Long id) {
        classGroupService.deleteClassGroupById(id);
    }
}
