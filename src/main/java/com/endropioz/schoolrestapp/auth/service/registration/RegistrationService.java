package com.endropioz.schoolrestapp.auth.service.registration;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenMessageDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

public interface RegistrationService {
    @Transactional
    void resendVerificationEmail(AuthEmailDto emailDto, HttpServletRequest request);

    void sendVerificationEmail(HttpServletRequest request, AuthEmailDto activatedUserDto);

    @Transactional
    UserResponseDto completeRegistration(AuthRegistrationRequestDto registrationDto, HttpServletRequest request);

    @Transactional
    VerificationTokenMessageDto confirmUserRegistration(String token, Locale locale);
}
