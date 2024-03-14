package com.endropioz.schoolrestapp.module.service;

import com.endropioz.schoolrestapp.module.dto.ModuleRequestDto;
import com.endropioz.schoolrestapp.module.dto.ModuleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.Set;

public interface ModuleService {
    Page<ModuleResponseDto> getAllModules(Pageable pageable);

    ModuleResponseDto getModuleById(Long id);

    ModuleResponseDto addNewModule(ModuleRequestDto dto);

    ModuleResponseDto updateModuleById(Long id, ModuleRequestDto dto);

    void deleteModule(Long id);

    Set<ModuleResponseDto> findModulesForNextWeek(ZonedDateTime startOfWeek, ZonedDateTime endOfWeek);
}
