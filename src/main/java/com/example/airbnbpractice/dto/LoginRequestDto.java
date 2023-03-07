package com.example.airbnbpractice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    @Schema(example = "test@test.com")
    private String email;
    @Schema(example = "Test1234!")
    private String password;
}