package com.endropioz.schoolrestapp.auth.service.registration;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthResponseDto;
import com.endropioz.schoolrestapp.auth.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationStrategy {
    @Transactional
    AuthResponseDto registerUser(AuthRegistrationRequestDto registrationDto);

    Role getSupportedRole();
}
