package com.endropioz.schoolrestapp.module.service.impl;

import com.endropioz.schoolrestapp.module.dto.ModuleRequestDto;
import com.endropioz.schoolrestapp.module.dto.ModuleResponseDto;
import com.endropioz.schoolrestapp.module.entity.Module;
import com.endropioz.schoolrestapp.module.mapper.ModuleMapper;
import com.endropioz.schoolrestapp.module.repository.ModuleRepository;
import com.endropioz.schoolrestapp.module.service.ModuleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModuleServiceImpl implements ModuleService {
    ModuleRepository moduleRepository;
    ModuleMapper moduleMapper = ModuleMapper.MAPPER;

    @Override
    @Transactional(readOnly = true)
    public Page<ModuleResponseDto> getAllModules(Pageable pageable) {
        Page<Module> modules = moduleRepository.findAll(pageable);

        return modules.map(moduleMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ModuleResponseDto getModuleById(Long id) {
        Module module = getExistingModuleById(id);

        return moduleMapper.toResponseDto(module);
    }

    @Override
    @Transactional
    public ModuleResponseDto addNewModule(ModuleRequestDto moduleDto) {
        return moduleMapper.toResponseDto(moduleRepository.save(moduleMapper.toEntity(moduleDto)));
    }

    @Override
    @Transactional
    public ModuleResponseDto updateModuleById(Long id, ModuleRequestDto moduleDto) {
        Module module = getExistingModuleById(id);

        moduleMapper.updateEntity(moduleDto, module);
        moduleRepository.save(module);

        return moduleMapper.toResponseDto(module);
    }

    @Override
    @Transactional
    public void deleteModule(Long id) {
        moduleRepository.findById(id).ifPresent(moduleRepository::delete);
    }

    @Override
    public Set<ModuleResponseDto> findModulesForNextWeek(ZonedDateTime startOfWeek, ZonedDateTime endOfWeek) {
        Set<Module> modulesForNextWeek = moduleRepository.findModulesForNextWeek(startOfWeek, endOfWeek);

        return moduleMapper.setOfModulesToSetOfModuleResponseDtos(modulesForNextWeek);
    }

    private Module getExistingModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found"));
    }
}
