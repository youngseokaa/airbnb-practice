package com.example.airbnbpractice.controller;

import com.example.airbnbpractice.common.dto.ResponseDto;
import com.example.airbnbpractice.dto.CheckResponseDto;
import com.example.airbnbpractice.dto.LoginRequestDto;
import com.example.airbnbpractice.dto.SignupRequestDto;
import com.example.airbnbpractice.dto.UserResponseDto;
import com.example.airbnbpractice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "User")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //회원가입
    @PostMapping("/api/users/signup")
    @SecurityRequirements()
    @Operation(summary = "회원가입하기", description = "회원가입하기 이메일,닉네임 중복 불가." +
            "isAdmin은 필수, isAdmin이 true면 adminToken을 확인")
    public ResponseDto<UserResponseDto> signup(@RequestBody @Validated SignupRequestDto signupRequestDto) {
         UserResponseDto userResponseDto = userService.signup(signupRequestDto);
        return ResponseDto.of(HttpStatus.OK,"회원가입에 성공하였습니다",userResponseDto);
    }

    // 이메일 중복 확인
    @GetMapping("/api/users/email/duplicate")
    @SecurityRequirements()
    @Operation(description = "이메일 중복 체크")
    public ResponseDto<CheckResponseDto> checkEmail(@RequestParam String email){
       CheckResponseDto checkResponseDto= userService.checkEmail(email);
        return ResponseDto.of(HttpStatus.OK,"사용가능으로 확인되었습니다",checkResponseDto);
    }

    // 닉네임 중복 확인
    @GetMapping("/api/users/nickName/duplicate")
    @SecurityRequirements()
    @Operation(description = "닉네임 중복 체크")
    public ResponseDto<CheckResponseDto> checkNickname(@RequestParam String nickName){
        CheckResponseDto checkResponseDto= userService.checkNickname(nickName);
        return ResponseDto.of(HttpStatus.OK,"사용가능으로 확인되었습니다",checkResponseDto);
    }

    // 로그인 하기
    @PostMapping("/api/users/login")
    @SecurityRequirements()
    @Operation(summary = "로그인 하기", description = "로그인 accessToken 발급")
    public ResponseDto<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseDto.of(HttpStatus.OK, "로그인 성공", userService.login(loginRequestDto, response));
    }
}