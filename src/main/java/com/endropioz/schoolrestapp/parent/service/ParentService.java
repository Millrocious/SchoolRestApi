package com.endropioz.schoolrestapp.parent.service;

import com.endropioz.schoolrestapp.parent.dto.ParentRequestDto;
import com.endropioz.schoolrestapp.parent.dto.ParentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParentService {
    ParentResponseDto addParent(ParentRequestDto parentRequestDto);

    Page<ParentResponseDto> getAllParents(Pageable pageable);

    ParentResponseDto getParentById(Long id);

    ParentResponseDto updateParentById(Long id, ParentRequestDto parentRequestDto);

    void deleteParentById(Long id);
}
