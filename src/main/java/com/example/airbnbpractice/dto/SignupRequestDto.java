package com.example.airbnbpractice.dto;

import com.sun.istack.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class SignupRequestDto {
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "(?=.*?[a-zA-Z])(?=.*?[\\d])(?=.*?[~!@#$%^&*()_+=\\-`]).{8,30}")
    private String password;

    @Email
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    @Schema(example = "false")
    private Boolean isAdmin;

    private String adminToken = "";
}
