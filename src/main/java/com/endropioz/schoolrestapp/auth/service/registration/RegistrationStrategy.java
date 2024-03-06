package com.endropioz.schoolrestapp.auth.service.registration;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationStrategy {
    @Transactional
    UserResponseDto registerUser(AuthRegistrationRequestDto registrationDto);

    Role getSupportedRole();
}
