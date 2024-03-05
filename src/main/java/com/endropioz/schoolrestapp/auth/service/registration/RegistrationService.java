package com.endropioz.schoolrestapp.auth.service.registration;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthResponseDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenMessageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

public interface RegistrationService {
    @Transactional
    void resendVerificationEmail(AuthEmailRequestDto emailDto, HttpServletRequest request);

    void sendVerificationEmail(HttpServletRequest request, AuthEmailRequestDto activatedUserDto);

    @Transactional
    AuthResponseDto completeRegistration(AuthRegistrationRequestDto registrationDto, HttpServletRequest request);

    @Transactional
    VerificationTokenMessageDto confirmUserRegistration(String token, Locale locale);
}
