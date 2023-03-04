package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.dto.SignupRequestDto;
import com.example.airbnbpractice.dto.UserResponseDto;
import com.example.airbnbpractice.security.UserDetailsImpl;
import com.example.airbnbpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //회원가입
    @PostMapping("/user/signup")
    public ResponseDto<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
         UserResponseDto userResponseDto = userService.signup(signupRequestDto);
        return ResponseDto.of(HttpStatus.OK,"회원가입에 성공하였습니다",userResponseDto);
    }
}