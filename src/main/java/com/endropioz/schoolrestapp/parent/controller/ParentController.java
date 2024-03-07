package com.endropioz.schoolrestapp.parent.controller;

import com.endropioz.schoolrestapp.parent.dto.ParentRequestDto;
import com.endropioz.schoolrestapp.parent.dto.ParentResponseDto;
import com.endropioz.schoolrestapp.parent.service.ParentService;
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
@RequestMapping("/api/parents")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ParentController {
    ParentService parentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParentResponseDto saveParent(@RequestBody @Valid ParentRequestDto parentRequestDto) {
        return parentService.addParent(parentRequestDto);
    }


    @GetMapping("/{id}")
    public ParentResponseDto getParentById(@PathVariable Long id) {
        return parentService.getParentById(id);
    }

    @GetMapping
    public Page<ParentResponseDto> getAllParents(@PageableDefault Pageable pageable) {
        return parentService.getAllParents(pageable);
    }

    @PutMapping("/{id}")
    public ParentResponseDto editParentById(@PathVariable Long id, @RequestBody @Valid ParentRequestDto parentRequestDto) {
        return parentService.updateParentById(id, parentRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameById(@PathVariable Long id) {
        parentService.deleteParentById(id);
    }
}
