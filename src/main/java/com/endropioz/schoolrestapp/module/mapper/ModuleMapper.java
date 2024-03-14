package com.endropioz.schoolrestapp.module.mapper;

import com.endropioz.schoolrestapp.module.dto.ModuleRequestDto;
import com.endropioz.schoolrestapp.module.dto.ModuleResponseDto;
import com.endropioz.schoolrestapp.module.dto.ModuleSubjectClassTeacherResponseDto;
import com.endropioz.schoolrestapp.module.entity.Module;
import com.endropioz.schoolrestapp.module.entity.ModuleSubjectClassTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ModuleMapper {

    ModuleMapper MAPPER = Mappers.getMapper(ModuleMapper.class);

    @Mapping(source = "schedule", target = "schedule")
    ModuleResponseDto toResponseDto(Module module);

    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "classGroup", target = "classGroup")
    @Mapping(source = "teacher", target = "teacher")
    ModuleSubjectClassTeacherResponseDto toResponseDto(ModuleSubjectClassTeacher teacher);

    Module toEntity(ModuleRequestDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ModuleRequestDto dto, @MappingTarget Module module);

    Set<ModuleResponseDto> setOfModulesToSetOfModuleResponseDtos(Set<Module> modules);

}