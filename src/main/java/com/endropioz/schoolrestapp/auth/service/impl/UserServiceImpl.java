package com.endropioz.schoolrestapp.auth.service.impl;

import com.endropioz.schoolrestapp.auth.dto.user.UserRequestDto;
import com.endropioz.schoolrestapp.auth.dto.user.UserResponseDto;
import com.endropioz.schoolrestapp.auth.entity.AccountStatus;
import com.endropioz.schoolrestapp.auth.entity.User;
import com.endropioz.schoolrestapp.auth.mapper.UserMapper;
import com.endropioz.schoolrestapp.auth.repository.UserRepository;
import com.endropioz.schoolrestapp.auth.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserMapper userMapper = UserMapper.MAPPER;

    @Override
    @Transactional
    public UserResponseDto saveRegisteredUser(UserRequestDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        String encodedPassword = passwordEncoder.encode(registrationDto.password());

        User newUser = User
                .builder()
                .email(registrationDto.email().toLowerCase())
                .password(encodedPassword)
                .role(registrationDto.role())
                .accountStatus(AccountStatus.DISABLED)
                .build();

        return userMapper.userToUserResponseDto(userRepository.save(newUser));
    }


    @Override
    @Transactional
    public UserResponseDto addUser(UserRequestDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User newUser = userMapper.toEntity(userDto);

        return userMapper.userToUserResponseDto(userRepository.save(newUser));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return userMapper.userToUserResponseDto(user.get());
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserResponseDto);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        userMapper.updateUserFromDto(userRequestDto, user);
        User updatedUser = userRepository.save(user);

        return userMapper.userToUserResponseDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String findVerificationTokenByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getVerificationToken)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
