package com.endropioz.schoolrestapp.auth.service;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenDto;
import org.springframework.transaction.annotation.Transactional;

public interface VerificationTokenService {
    @Transactional
    void createVerificationToken(AuthEmailRequestDto email, String token);

    VerificationTokenDto getVerificationToken(String VerificationToken);
}
