package com.endropioz.schoolrestapp.auth.listener;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailDto;
import com.endropioz.schoolrestapp.auth.event.OnRegistrationCompleteEvent;
import com.endropioz.schoolrestapp.auth.service.VerificationTokenService;
import com.endropioz.schoolrestapp.mail.service.MailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    MailService mailService;
    VerificationTokenService verificationTokenService;
    Environment environment;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        AuthEmailDto userDto = event.getUserDto();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(userDto, token);

        String recipientAddress = userDto.email();
        String subject = environment.getProperty("email.registration.subject", "Registration Confirmation");
        String confirmationUrl = "http://localhost:8080" + event.getAppUrl() + "/api/public/registration/confirmation?token=" + token;
        String emailBody = environment.getProperty("email.registration.body", "Your registration was successful!") + "\r\n" + confirmationUrl;

        mailService.sendEmail(recipientAddress, subject, emailBody);
    }
}