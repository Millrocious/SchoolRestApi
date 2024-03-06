package com.endropioz.schoolrestapp.auth.service.impl.registration.strategy;

import com.endropioz.schoolrestapp.auth.dto.auth.AuthRegistrationRequestDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserRequestDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.entity.Role;
import com.endropioz.schoolrestapp.auth.service.UserService;
import com.endropioz.schoolrestapp.auth.service.registration.RegistrationStrategy;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentRegistrationStrategy implements RegistrationStrategy {

    StudentRepository studentRepository;
    UserService userService;

    @Override
    public UserResponseDto registerUser(AuthRegistrationRequestDto registrationDto) {
        if (!studentRepository.existsByEmailAndDeletedIsFalse(registrationDto.email())) {
            throw new IllegalStateException("No data about user with email: " + registrationDto.email());
        }

        UserRequestDto newUserDto = new UserRequestDto(
                registrationDto.email(),
                registrationDto.password(),
                Role.STUDENT
        );

        return userService.saveRegisteredUser(newUserDto);
    }

    @Override
    public Role getSupportedRole() {
        return Role.STUDENT;
    }
}
