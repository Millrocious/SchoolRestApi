package com.endropioz.schoolrestapp.auth.service;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenDto;
import org.springframework.transaction.annotation.Transactional;

public interface VerificationTokenService {
    @Transactional
    void createVerificationToken(AuthEmailDto email, String token);

    VerificationTokenDto getVerificationToken(String VerificationToken);
}
