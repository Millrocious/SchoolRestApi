package com.endropioz.schoolrestapp.auth.service.impl.registration;

import com.endropioz.schoolrestapp.auth.config.AuthMessages;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthResponseDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenMessageDto;
import com.endropioz.schoolrestapp.auth.entity.AccountStatus;
import com.endropioz.schoolrestapp.auth.entity.User;
import com.endropioz.schoolrestapp.auth.service.UserService;
import com.endropioz.schoolrestapp.auth.service.VerificationTokenService;
import com.endropioz.schoolrestapp.auth.service.registration.RegistrationService;
import com.endropioz.schoolrestapp.auth.event.OnRegistrationCompleteEvent;
import com.endropioz.schoolrestapp.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationServiceImpl implements RegistrationService {
    UserService userService;

    ApplicationEventPublisher eventPublisher;

    UserRepository userRepository;

    MessageSource messages;

    VerificationTokenService verificationTokenService;

    AuthMessages authMessages;

    RegistrationStrategyFactory strategyFactory;

    @Override
    @Transactional
    public void resendVerificationEmail(AuthEmailRequestDto emailDto, HttpServletRequest request) {
        userService.getUserByEmail(emailDto.email());
        sendVerificationEmail(request, emailDto);
    }

    @Override
    public void sendVerificationEmail(HttpServletRequest request, AuthEmailRequestDto activatedUserDto) {
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(activatedUserDto,
                request.getLocale(),
                request.getContextPath()));
    }

    @Override
    @Transactional
    public VerificationTokenMessageDto confirmUserRegistration(String token, Locale locale) {
        VerificationTokenDto verificationTokenDto = verificationTokenService.getVerificationToken(token);
        if (Objects.isNull(verificationTokenDto)) {
            return new VerificationTokenMessageDto(false, authMessages.getInvalidToken(), HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByVerificationToken(token);
        if (user.isEmpty()) {
            String message = messages.getMessage("auth.message.tokenNotFound", null, locale);

            return new VerificationTokenMessageDto(false, message, HttpStatus.BAD_REQUEST);
        }

        if (verificationTokenDto.isExpired()) {
            return new VerificationTokenMessageDto(Boolean.FALSE, authMessages.getExpired(), HttpStatus.BAD_REQUEST);
        }

        user.get().setAccountStatus(AccountStatus.ACTIVE);
        userRepository.save(user.get());

        return new VerificationTokenMessageDto(true, authMessages.getConfirmed(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public AuthResponseDto completeRegistration(AuthRegistrationRequestDto registrationDto, HttpServletRequest request) {
        AuthResponseDto responseDto =
                strategyFactory.get(registrationDto.userType()).registerUser(registrationDto);

        sendVerificationEmail(request, new AuthEmailRequestDto(registrationDto.email()));

        return responseDto;
    }
}
