package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.dto.ErrorMessage;
import com.example.airbnbpractice.dto.SignupRequestDto;
import com.example.airbnbpractice.dto.UserResponseDto;
import com.example.airbnbpractice.entity.User;
import com.example.airbnbpractice.entity.UserRoleEnum;
import com.example.airbnbpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${admin_token}")
    private String ADMIN_TOKEN;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserResponseDto signup(SignupRequestDto signupRequestDto) {

        if(userRepository.findByEmailAndNickname(signupRequestDto.getEmail(), signupRequestDto.getNickname()).isPresent()) {
            throw CustomClientException.of(ErrorMessage.DUPLE_USER);
        }

        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        if(signupRequestDto.getIsAdmin() && Objects.equals(ADMIN_TOKEN, signupRequestDto.getAdminToken())){
            userRoleEnum = UserRoleEnum.ADMIN;
        }
        User user = User.builder()
                .email(signupRequestDto.getEmail())
                .password(password)
                .nickname(signupRequestDto.getNickname()).userRoleEnum(userRoleEnum).build();

        user = userRepository.save(user);

        return  new UserResponseDto(user);
    }
}
