package com.endropioz.schoolrestapp.attendance.mapper;

import com.endropioz.schoolrestapp.attendance.dto.AttendanceRequestDto;
import com.endropioz.schoolrestapp.attendance.dto.AttendanceResponseDto;
import com.endropioz.schoolrestapp.attendance.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper MAPPER = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(target = "lessonId", source = "entity.lesson.id")
    @Mapping(target = "studentId", source = "entity.student.id")
    AttendanceResponseDto toResponseDto(Attendance entity);

    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    Attendance toEntity(AttendanceRequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntity(AttendanceRequestDto requestDto, @MappingTarget Attendance entity);
}
