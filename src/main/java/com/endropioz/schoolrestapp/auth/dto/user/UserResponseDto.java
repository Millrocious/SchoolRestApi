package com.endropioz.schoolrestapp.auth.dto.user;

import com.endropioz.schoolrestapp.auth.entity.AccountStatus;
import com.endropioz.schoolrestapp.auth.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserResponseDto(
        Long id,
        String email,
        Role role,
        AccountStatus accountStatus,
        @JsonIgnore
        String password
) {
}

