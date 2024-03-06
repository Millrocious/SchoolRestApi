package com.endropioz.schoolrestapp.auth.security;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthRequestDto;
import com.endropioz.schoolrestapp.auth.dto.auth.AuthResponseDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.dto.token.RefreshTokenRequestDto;
import com.endropioz.schoolrestapp.auth.dto.token.RefreshTokenResponseDto;
import com.endropioz.schoolrestapp.auth.entity.AccountStatus;
import com.endropioz.schoolrestapp.auth.entity.User;
import com.endropioz.schoolrestapp.auth.exception.UserIsNotActiveException;
import com.endropioz.schoolrestapp.auth.mapper.UserMapper;
import com.endropioz.schoolrestapp.auth.service.UserService;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    JwtService jwtService;

    PasswordEncoder passwordEncoder;

    UserService userService;

    UserMapper userMapper = UserMapper.MAPPER;

    public void authenticate(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String authHeader = refreshTokenRequestDto.refreshToken();

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Missing refresh token");
        }

        String refreshToken = authHeader.substring(7);

        if (jwtService.extractType(refreshToken).equals("accessToken")) {
            throw new JwtException("Access token cannot be used for refreshing token");
        }

        UserResponseDto userResponseDto = userService.getUserByEmail(jwtService.extractEmail(refreshToken));
        User user = userMapper.userResponseDtoToUser(userResponseDto);

        if (user.getAccountStatus().equals(AccountStatus.ACTIVE)) {
            return new RefreshTokenResponseDto(jwtService.generateAccessToken(user), jwtService.generateRefreshToken(user));
        } else {
            throw new UserIsNotActiveException("User account is not valid");
        }
    }

    public AuthResponseDto login(AuthRequestDto authenticationRequestDto) {
        UserResponseDto userResponseDto = userService.getUserByEmail(authenticationRequestDto.email());
        User user = userMapper.userResponseDtoToUser(userResponseDto);

        if (user.getAccountStatus().equals(AccountStatus.ACTIVE)) {
            if (passwordEncoder.matches(authenticationRequestDto.password(), user.getPassword())) {
                return new AuthResponseDto(jwtService.generateAccessToken(user), jwtService.generateRefreshToken(user));
            } else {
                throw new BadCredentialsException("Wrong password");
            }
        } else {
            throw new UserIsNotActiveException("User account is not valid");
        }
    }
}
