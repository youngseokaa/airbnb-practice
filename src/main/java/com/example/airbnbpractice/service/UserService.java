package com.example.airbnbpractice.service;

import com.example.airbnbpractice.common.CustomClientException;
import com.example.airbnbpractice.common.Jwt.JwtUtil;
import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.dto.CheckResponseDto;
import com.example.airbnbpractice.dto.LoginRequestDto;
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

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.example.airbnbpractice.common.dto.ErrorMessage.*;
import static com.example.airbnbpractice.common.dto.ErrorMessage.NOT_MATCH_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    @Value("${admin_token}")
    private String ADMIN_TOKEN;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserResponseDto signup(SignupRequestDto signupRequestDto) {

        if(userRepository.findByEmailAndNickname(signupRequestDto.getEmail(), signupRequestDto.getNickname()).isPresent()) {
            throw CustomClientException.of(DUPLE_USER);
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


    public UserResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> CustomClientException.of(NO_USER)
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw CustomClientException.of(NOT_MATCH_PASSWORD);
        }
//ㅁㄴㅇㄻㄴㄹㅇ
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));

        return new UserResponseDto(user);
    }

    public CheckResponseDto checkEmail(String email) {
        // 중복 이메일 확인
        if(userRepository.findByEmail(email).isPresent()){
            throw CustomClientException.of(EXIST_EMAIL);
        }

        return new CheckResponseDto();
    }

    public CheckResponseDto checkNickname(String nickName) {
        // 중복 닉네임 확인
        if(userRepository.findByNickname(nickName).isPresent()){
            throw CustomClientException.of(EXIST_NICKNAME);
        }

        return new CheckResponseDto();
    }
}
