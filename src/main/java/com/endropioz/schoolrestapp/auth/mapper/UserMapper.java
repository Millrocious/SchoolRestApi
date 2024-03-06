package com.endropioz.schoolrestapp.auth.mapper;

import com.endropioz.schoolrestapp.auth.dto.user.UserRequestDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDto dto);

    User userResponseDtoToUser(UserResponseDto dto);

    UserResponseDto userToUserResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}
