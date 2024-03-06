package com.endropioz.schoolrestapp.auth.controller;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthEmailDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.VerificationTokenMessageDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.service.registration.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/public/registration")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationController {
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRegistrationRequestDto registrationDto,
                                          HttpServletRequest request) {
        try {
            UserResponseDto responseDto = registrationService.completeRegistration(registrationDto, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationEmail(@Valid @RequestBody AuthEmailDto emailDto,
                                                     HttpServletRequest request) {
        try {
            registrationService.resendVerificationEmail(emailDto, request);
            return ResponseEntity.ok("Verification email sent successfully to: " + emailDto.email());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("User with given email does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/confirmation")
    public ResponseEntity<?> confirmation(@RequestParam("token") String token, WebRequest request) {
        VerificationTokenMessageDto result = registrationService.confirmUserRegistration(token, request.getLocale());

        return ResponseEntity.status(result.status())
                .body(new ApiResponse(result.success(), result.message()));
    }

    public record ApiResponse(boolean success, String message) {
    }
}
