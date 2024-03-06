package com.endropioz.schoolrestapp.auth.controller;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthResponseDto;
import com.endropioz.schoolrestapp.auth.dto.token.RefreshTokenRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.RefreshTokenResponseDto;
import com.endropioz.schoolrestapp.auth.security.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthService authenticationService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authenticationRequestDto) {
        return authenticationService.login(authenticationRequestDto);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponseDto refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        return authenticationService.refreshToken(new RefreshTokenRequestDto(authHeader));
    }
}
