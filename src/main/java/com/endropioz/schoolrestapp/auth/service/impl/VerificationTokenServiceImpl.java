package com.endropioz.schoolrestapp.auth.service.impl;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenDto;
import com.endropioz.schoolrestapp.auth.entity.User;
import com.endropioz.schoolrestapp.auth.repository.UserRepository;
import com.endropioz.schoolrestapp.auth.service.VerificationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Value("${verification.token.expiration}")
    int expiryTimeInMinutes;

    final UserRepository userRepository;

//    public VerificationTokenServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    @Transactional
    public void createVerificationToken(AuthEmailRequestDto emailDto, String token) {
        String email = emailDto.email().toLowerCase();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        user.updateVerificationToken(token, calculateExpiryDate());
        userRepository.save(user);
    }

    @Override
    public VerificationTokenDto getVerificationToken(String token) {
        return userRepository.findByVerificationToken(token)
                .filter(user -> !user.isVerificationTokenExpired())
                .map(user -> new VerificationTokenDto(user.getVerificationToken(), user.getTokenExpiryDate()))
                .orElse(null);
    }

    private LocalDateTime calculateExpiryDate() {
        return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
    }
}
